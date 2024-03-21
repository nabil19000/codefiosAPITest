package api.testCases;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static api.testCases.BearerToken.token;
import static io.restassured.RestAssured.*;

public class ReadAllAccount extends BearerToken {



     String endpoint1 = "/account/getAll";
    String BASE_URL = "https://qa.codefios.com/api";
    @Test
     public void readAllAccount(){


Response response =
         given()
                 .baseUri(BASE_URL)
                 .header("Content-Type","application/json")
                 .header("Authorization","Bearer "+ generateBearerToken())
                 .log().all()

                 .when()
                      .get(endpoint1)


                 .then()
                 .statusCode(200)
                 .header("content-type","application/json")
                 .cookie("ci_session")


                 .log().all()
                         .extract()
                                 .response();
        System.out.println(generateBearerToken());
    }


}
