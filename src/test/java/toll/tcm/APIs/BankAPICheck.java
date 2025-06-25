package toll.tcm.APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class BankAPICheck {

	public static void main(String[] args) {
       String baseUri = "https://www.nmswaifal.in/BankApiCall/BankApiCall/ReqTagDetails/34161FA8203289720A9BA480";
        
        // Set RestAssured base URI
        RestAssured.baseURI = baseUri;
        
        // Set the timeout in milliseconds
        int timeout = 5000;
        
        // Make the POST request with an empty JSON body and timeout settings
        Response response = RestAssured
            .given()
            .contentType("application/json")
            .body("{}") // Empty JSON body
            .when()
            .post()
            .then()
            .extract()
            .response();
        
        // Print the response status code and body
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

	}

}
