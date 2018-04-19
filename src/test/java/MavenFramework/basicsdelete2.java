package MavenFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import googlePlaceAPI.payLoad;
import googlePlaceAPI.resources1;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class basicsdelete2 {
    private static Logger log= LogManager.getLogger(basicsdelete2.class.getName());

    Properties prop =new Properties();
    @BeforeTest
    public void getData() throws IOException{
        FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
    }
    
    @Test
    public void AddanddeletePlace() {
        log.info("Host Information is" + prop.getProperty("HOST"));
        
        RestAssured.baseURI=prop.getProperty("HOST");
        Response res=given().
        
        queryParam("key", prop.get("KEY")).
        body(payLoad.postBodyData()).
          when().
          post(resources1.addplace()).
          then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
          body("status", equalTo("OK")).
          extract().response();
        log.info("Raw json response is" + res );
        
        String stringresponse=res.asString();
        System.out.println(stringresponse);
        log.info(stringresponse);
        JsonPath js=new JsonPath(stringresponse);
        String placeid=js.get("place_id");
        System.out.println(placeid);
        log.info("The place id added is" + placeid);
        given().
        queryParam("key",prop.getProperty("KEY")).
        body("{"+
            "\"place_id\": \""+placeid+"\""+
        "}").
        when().
        post(resources1.deletePlace()).
        then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
        body("status",equalTo("OK"));
        }
        
}

