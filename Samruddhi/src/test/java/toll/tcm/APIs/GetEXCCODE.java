package toll.tcm.APIs;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetEXCCODE
{
	public static void main(String[]args)
	{
		String baseUri="https://uat.pecl-panipat.com/BankAPICall/BankApiCall/ReqTagByVRNDetails/";
		RequestSpecification httpRequest=RestAssured.given();
		JSONParser jsonparser =new JSONParser();
		String postEndpoint = "DL01T1120";
		String requestBody="";
		RestAssured.baseURI = baseUri;
		RequestSpecification requestSpec = RestAssured.given();

        // Set the request content type as JSON
        requestSpec.contentType("application/json");

        // Set the request body
        requestSpec.body(requestBody);

        // Send the POST request and get the response
        Response response = requestSpec.post(postEndpoint);
        System.out.println("endPoint:"+postEndpoint);
        // Print the response body
        System.out.println("Response Body: ");
        
        String StringReponse=response.getBody().asString();
        System.out.println(StringReponse);
        
        int Firstindex=StringReponse.indexOf("EXCCODE");
        String EXCCODE=StringReponse.substring(Firstindex).substring(10, 12);
        int Startindex=(StringReponse.substring(Firstindex).substring(10, 12).indexOf("-"))+1;
        String InterString=StringReponse.substring(Firstindex).substring(Startindex+10);
        
        System.out.println("------"+InterString);
        int LastIndex=InterString.indexOf("|");
     
        String allcode=InterString.substring(Startindex,LastIndex);
        System.out.println(allcode);
       System.out.println("\"\"");

        // Print the status code
        System.out.println("Status Code: " + response.getStatusCode());
	}
	
	 public static String getEXCCODE(String bankAPI,String VRN) throws Exception
	{
		
		
		String baseUri=bankAPI;
		RequestSpecification httpRequest=RestAssured.given();
		JSONParser jsonparser =new JSONParser();
		String postEndpoint = VRN;
		String requestBody="";
		RestAssured.baseURI = baseUri;
		RequestSpecification requestSpec = RestAssured.given();

        // Set the request content type as JSON
        requestSpec.contentType("application/json");

        // Set the request body
        requestSpec.body(requestBody);

        // Send the POST request and get the response
        Response response = requestSpec.post(postEndpoint);
        System.out.println("endPoint:"+postEndpoint);
        // Print the response body
        System.out.println("Response Body: ");
        
        String StringReponse=response.getBody().asString();
        System.out.println(StringReponse);
        if(StringReponse.equals("\"\""))
        {
        	return null;
        }
        else if(StringReponse.equals("\"00\""))
        {
        	return StringReponse;
        }
        else
        {
	        int Firstindex=StringReponse.indexOf("EXCCODE");
	        String EXCCODE=StringReponse.substring(Firstindex).substring(10, 12);
	        int Startindex=(StringReponse.substring(Firstindex).substring(10, 12).indexOf("-"))+1;
	        String InterString=StringReponse.substring(Firstindex).substring(Startindex+10);
	        
	        System.out.println("------"+InterString);
	        int LastIndex=InterString.indexOf("|");
	        System.out.println("LastIndex:"+LastIndex);
	        String allcode="";
	        if(LastIndex==-1)
	        {
	        	allcode=InterString.substring(Startindex);
	        }
	        else
	        {
	        	allcode=InterString.substring(Startindex,LastIndex);
	        }
	        
	        System.out.println(allcode);
	       
	
	
	        // Print the status code
	        System.out.println("Status Code: " + response.getStatusCode());
	        return allcode;
		}
	}
		
		
		
		
	
	
}
