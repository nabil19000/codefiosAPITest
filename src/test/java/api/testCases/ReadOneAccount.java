package api.testCases;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class ReadOneAccount {
    String userName = "demo1@codefios.com";
    String password = "abc123";
    String endpointReadOneAccount = "/account/getOne";
    String BASE_URL = "https://qa.codefios.com/api";
    @Test
    public void readOneAccount(){
        Response response =

        given()
                .baseUri(BASE_URL)
                .header("Content-Type","application/json")
                .auth().preemptive().basic(userName,password)
                .queryParam("account_id","419")


                .when()
                .get(endpointReadOneAccount)



                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();




    }
}
