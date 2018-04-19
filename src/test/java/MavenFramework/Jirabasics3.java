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

public class Jirabasics3 {
    Properties prop =new Properties();
    
    @BeforeTest
    
    public void getData() throws IOException{
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//env.properties");
        prop.load(fis);
    }
      @Test
    public void UpdateCommentGeneric(){
          String sessionid=ReusableMethods.getSessionKey();
          String issueId=ReusableMethods.getIssueId(sessionid);
          String commentId=ReusableMethods.getCommentId(issueId,sessionid);
         RestAssured.baseURI=prop.getProperty("JIRAHOST");
        given().
         header("Content-Type","application/json").
         header("Cookie","JSESSIONID="+sessionid).
         pathParam("issuekey",issueId).
         pathParam("commentid",commentId).
         body(payLoad.postBodyUpdateComment()).
         when().
                put(resources1.jiraCommonRes()+"/{issuekey}"+"/comment"+"/{commentid}").then()
                .statusCode(200).extract().response();
      }
        
    }

