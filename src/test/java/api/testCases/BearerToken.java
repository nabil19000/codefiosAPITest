package api.testCases;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class BearerToken {
    String BASE_URL = "https://qa.codefios.com/api";
    String endpoint = "/user/login";
    String username = "admin";
    String password = "123456";
   public static String token;

   public BearerToken(){
       generateBearerToken();
   }


    @Test
    public String generateBearerToken(){

      Response response = given()
                .baseUri(BASE_URL)
                .header("Content-Type","application/json")
                .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                .when()
                .post( endpoint)
                .then()
                .statusCode(201)
                .header("Content-Type","application/json")
                .cookie("ci_session")
                .time(lessThan(2000L), TimeUnit.MILLISECONDS)
                .header("server","LiteSpeed")
                .header("alt-svc","h3=\":443\"; ma=2592000, h3-29=\":443\"; ma=2592000, h3-Q050=\":443\"; ma=2592000, h3-Q046=\":443\"; ma=2592000, h3-Q043=\":443\"; ma=2592000, quic=\":443\"; ma=2592000; v=\"43,46\"")

                .extract()
                .response();

         token = response.jsonPath().getString("access_token");
        System.out.println( token);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,201);
        String header = response.getHeader("Content-Type");
        Assert.assertEquals(header,"application/json");
       long time = response.getTimeIn(TimeUnit.MILLISECONDS);
       if (time<2000){
           System.out.println("response time is within range.");

       }else{
           System.out.println("time response is out of range !.");
       }
       return token;
    }
}
