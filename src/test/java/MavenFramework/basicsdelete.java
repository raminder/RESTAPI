package MavenFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import MavenFramework.ReusableMethods;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class basicsdelete {

    @Test
    public void deletePlaceXML() throws IOException {
        
        String bodyData= GenerateStringFromResource("D:\\DATA\\Study\\postData.xml");
        String deleteData= GenerateStringFromResource("D:\\DATA\\Study\\deleteData.xml");
        RestAssured.baseURI="https://maps.googleapis.com";
        Response res=given().
        
        queryParam("key", "AIzaSyB_bT0dCgKrBcDB1B42aUjZO5m9hBtvp_o").
        body(bodyData).
          when().
          post("/maps/api/place/add/xml").
          then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
          //body("status", equalTo("OK")).
          extract().response();
        System.out.println(res);
        
        /*String stringresponse=res.asString();
        System.out.println(stringresponse);
        XmlPath xml=new XmlPath(stringresponse);*/
        XmlPath xml=ReusableMethods.rawToXML(res);
        String placeid=xml.get("PlaceAddResponse.place_id");
        System.out.println(placeid);
        
       given().
        queryParam("key","AIzaSyB_bT0dCgKrBcDB1B42aUjZO5m9hBtvp_o").
        body(deleteData.replace("place_id", placeid)).
        when().
        post("/maps/api/place/delete/xml").
        then().assertThat().statusCode(200).and().contentType(ContentType.XML);
       // body("status",equalTo("OK"));
        System.out.println("The placed is deleted successfully");
        }
    
    public static String GenerateStringFromResource(String path) throws IOException{
        return new String(Files.readAllBytes(Paths.get(path)));
    }
        
}

