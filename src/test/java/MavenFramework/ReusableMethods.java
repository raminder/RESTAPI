package MavenFramework;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import jiraAPI.resources1;

public class ReusableMethods {

    public static XmlPath rawToXML(
            Response res) {
        String stringresponse = res.asString();
        XmlPath xml = new XmlPath(stringresponse);
        return xml;

    }

    public static JsonPath rawToJSON(
            Response res) {
        String stringresponse = res.asString();
        JsonPath json = new JsonPath(stringresponse);
        return json;
    }

    public static String getSessionKey() {
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json")
                .body("{ \"username\": \"raminderkaur\", \"password\": \"Waheguru@1234\" }").when()
                .post(resources1.getSession()).then().statusCode(200).extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String sessionid = js.get("session.value");
        System.out.println(sessionid);
        return sessionid;
    }

    public static String getIssueId(String sessionid) {
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + sessionid)
                .body("{" + "\"fields\": {" + "\"project\":{" + "\"key\": \"RES\"" + " },"
                        + "\"summary\": \"REST automated issue creation new\","
                        + "\"description\": \"Creating of an issue using automated script\"," + "\"issuetype\": {"
                        + "\"name\": \"Bug\"" + "}" + "}}")
                .when().post("/rest/api/2/issue").then().statusCode(201).extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String id = js.get("id");
        System.out.println(id);
        return id;
    }

    public static String getCommentId(String id,String sessionid) {
        RestAssured.baseURI = "http://localhost:8080";
        Response res = given().header("Content-Type", "application/json")
                .header("Cookie", "JSESSIONID=" + sessionid)
                .body("{\"body\": \"Add comment via Automation\","+
                "\"visibility\": {" + "\"type\": \"role\","
                        + "\"value\": \"Administrators\"}" + "}")
                .when().post("/rest/api/2/issue/" + id +"/comment").then().statusCode(201).extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String commentid = js.get("id");
        System.out.println(commentid);
        System.out.println(id);
        return commentid;
    }

}
