package MavenFramework;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class basics {
    
    @Test

    public void getPlace() {
        
        RestAssured.baseURI="https://maps.googleapis.com";
        
        given().
               param("location","31.6340,74.8723").
               param("radius","500").
               param("key","AIzaSyB_bT0dCgKrBcDB1B42aUjZO5m9hBtvp_o").
               when().
               get("/maps/api/place/nearbysearch/json").
               then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
               //body("results[0].geometry.location.lat",equalTo("31.6339793")).and().
               body("results[0].name",equalTo("Global Industries")).and().
               body("results[0].place_id",equalTo("ChIJVXOeVqpkGTkRW-ZuZMBW_nc"));
        
    }

}
