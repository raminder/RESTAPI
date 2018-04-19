package MavenFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import MavenFramework.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jiraAPI.payLoad;
import jiraAPI.resources1;

public class Jirabasics2 {
    Properties prop =new Properties();
    
    @BeforeTest
    
    public void getData() throws IOException{
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
    }
      @Test
    public void addComment(){
          String sessionid=ReusableMethods.getSessionKey();
          String issueid=ReusableMethods.getIssueId(sessionid);
         RestAssured.baseURI=prop.getProperty("JIRAHOST");
        Response res= given().
         header("Content-Type","application/json").
         header("Cookie","JSESSIONID="+sessionid).
         pathParam("issuekey",issueid).
         body(payLoad.postBodyAddcomment()).
         when().
         post(resources1.jiraCommonRes()+"/{issuekey}"+"/comment").then().statusCode(201).
         extract().response();
        JsonPath js =ReusableMethods.rawToJSON(res);
        String commentid= js.get("id");
        System.out.println(commentid);
      }
}   
