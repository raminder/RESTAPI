package MavenFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import MavenFramework.ReusableMethods;
import jiraAPI.payLoad;
import jiraAPI.resources1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Jirabasics {
    Properties prop =new Properties();
    
    @BeforeTest
    
    public void getData() throws IOException{
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
    }
      @Test
    public void createIssue(){
          String sessionid=ReusableMethods.getSessionKey();
         RestAssured.baseURI=prop.getProperty("JIRAHOST");
        Response res= given().
         header("Content-Type","application/json").
         header("Cookie","JSESSIONID="+sessionid).
         body(payLoad.postBodyCreateIssue()).
         when().
         post(resources1.jiraCommonRes()).then().statusCode(201).
         extract().response();
        JsonPath js = ReusableMethods.rawToJSON(res);
        String id=js.get("id");
        System.out.println(id);
    }
        
    }

