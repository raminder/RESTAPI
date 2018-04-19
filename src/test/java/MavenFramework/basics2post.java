package MavenFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import googlePlaceAPI.resources1;
import googlePlaceAPI.payLoad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class basics2post{
    private static Logger log= LogManager.getLogger(basics2post.class.getName());
    Properties prop =new Properties();
    @BeforeTest
    public void getData() throws IOException{
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
    }
    
@Test
public void AddPlace() {
    log.info("Host Information"+ prop.getProperty("HOST"));

    RestAssured.baseURI=prop.getProperty("HOST");
    given().
    queryParam("key", prop.getProperty("KEY")).
    body(payLoad.postBodyData()).
      when().
      post(resources1.addplace()).
      then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
      body("status", equalTo("OK"));
      }
}
