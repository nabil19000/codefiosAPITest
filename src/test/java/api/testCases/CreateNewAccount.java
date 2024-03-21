package api.testCases;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class CreateNewAccount extends BearerToken{
    String accountId;
    String endpointNewAccount = "/account/create";
    String BASE_URL = "https://qa.codefios.com/api";
          String requestBody = "{\n" +
            "  \"account_name\": \"charlie\",\n" +
            "  \"description\": \"saving\",\n" +
            "  \"balance\": 100000.78,\n" +
            "  \"account_number\": 100004500,\n" +
            "  \"contact_person\": \"alain\"\n" +
            "}";


    @Test
    public void createNewAccount(){
Response response =
        given()
                .baseUri(BASE_URL)
                .header("Content-Type","application/json")
                .header("Authorization","Bearer "+generateBearerToken())
                .body(requestBody)

                .when()
                .post(endpointNewAccount)


                .then()
                .statusCode(201)
                .header("content-type","application/json")
                .log().all()
                .extract()
                .response();

        // Extracting the account ID from the response body using JsonPath
        JsonPath jsonPath = response.jsonPath();
        accountId = jsonPath.getString("account_id");

        // Displaying the created account and storing the account ID
        System.out.println("Created Account: " + response.getBody().asString());
        System.out.println("Account ID: " + accountId);

    }
}
