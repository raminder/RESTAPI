package MavenFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import MavenFramework.ReusableMethods;
import googlePlaceAPI.resources1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class basics2 {
    
    Properties prop = new Properties();
    @BeforeTest
    public void getData() throws IOException{
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
        }
    
    @Test

    public void createPlace() {
        
        RestAssured.baseURI=prop.getProperty("HOST");
        
       Response res= given().
               param("location","31.6340,74.8723").
               param("radius","500").
               param("key",prop.getProperty("KEY")).log().all().
               when().
               get(resources1.searchPlace()).
               then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
               //body("results[0].geometry.location.lat",equalTo("31.6339793")).and().
               body("results[0].name",equalTo("Global Industries")).and().
               body("results[0].place_id",equalTo("ChIJVXOeVqpkGTkRW-ZuZMBW_nc")).
               extract().response();
       JsonPath js= ReusableMethods.rawToJSON(res);
       int count=js.get("results.size()");
       for(int i=0; i<count; i++){
          String name= js.get("results["+i+"].name");
           System.out.println("The name is " + name);
           }
       
    }

}
