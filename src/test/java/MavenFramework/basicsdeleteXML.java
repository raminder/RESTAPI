package MavenFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class basicsdeleteXML {

    @Test
    public void AdddeletePlace1() {
        
        String b="{"+
                " \"location\": {"+
                "\"lat\": 31.7444,"+
                "\"lng\": 74.9163"+
              "},"+
              "\"accuracy\": 50,"+
              "\"name\": \"Sohian Kalan Village!\","+
              "\"phone_number\": \"(02) 9374 4000\","+
              "\"address\": \"VPO Sohian Kalan, Majitha, 14601, India\","+
              "\"types\": [\"atm\"],"+
              "\"language\": \"en\""+
        "}";
        RestAssured.baseURI="https://maps.googleapis.com";
        Response res=given().
        
        queryParam("key", "AIzaSyB_bT0dCgKrBcDB1B42aUjZO5m9hBtvp_o").
        body(b).
          when().
          post("/maps/api/place/add/json").
          then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
          body("status", equalTo("OK")).
          extract().response();
        
        String stringresponse=res.asString();
        System.out.println(stringresponse);
        JsonPath js=new JsonPath(stringresponse);
        String placeid=js.get("place_id");
        System.out.println(placeid);
        
        given().
        queryParam("key","AIzaSyB_bT0dCgKrBcDB1B42aUjZO5m9hBtvp_o").
        body("{"+
            "\"place_id\": \""+placeid+"\""+
        "}").
        when().
        post("/maps/api/place/delete/json").
        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
        body("status",equalTo("OK"));
        }
        
}

