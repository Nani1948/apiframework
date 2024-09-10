package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.placevalidations.pojo.AddPlace;
import com.placevalidations.pojo.Location;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import resources.TestDataBuild;
import resources.Utils;
import resources.constant.APIResources;

import static  org.junit.Assert.*;

public class PlaceValidations extends Utils{
	ResponseSpecification resSpec=null;
	RequestSpecification  resS=null;
	Response response =null;
	TestDataBuild data=new TestDataBuild();
	static String placeId=null;
    
	@Given("Add Place Payload with {string} {string} {string}.")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	

		 
	
		 resS=given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));		 
	   
		/*Response response =resS.when().post("/maps/api/place/add/json").
				then().spec(resspec ).extract().response();	*/
	}

	@When("User calls {string} with {string} http request.")
	public void user_calls_with_post_http_request(String resource,String method) 
	{
		//Constructor will be called with value of resource which you pass.
       APIResources resourceAPI= APIResources.valueOf(resource);
       System.out.println(resourceAPI.getResource());
       resSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();	
    
       if(method.equalsIgnoreCase("POST")) {
    	 response =resS.when().post(resourceAPI.getResource());		
       }
       else if(method.equalsIgnoreCase("GET")) {
    	response =resS.when().get(resourceAPI.getResource());		
       }
       else if(method.equalsIgnoreCase("DELETE")) 
       {
    	response =resS.when().delete(resourceAPI.getResource());		
       }
	}

	@Then("API call got success with status code {int}.")
	public void api_call_got_success_with_status_code(Integer int1) {
      assertEquals(response.getStatusCode(),200); 
	  
	  
	}

	@Then("{string} in response body is {string}.")
	public void in_response_body_is(String key1, String value1) {

	  assertEquals(  getJsonPath(response, key1),value1);
	
	}

	

	@Then("Verify place_Id created maps to {string} using {string}.")
	public void verify_place_id_created_maps_to_using(String exceptedName, String resource) throws IOException {
		 placeId=getJsonPath(response, "place_id");
		 resS=given().spec(requestSpecification()).queryParam("place_id", placeId);
		 user_calls_with_post_http_request(resource, "GET");
		  String actualName=getJsonPath(response, "name");
		  assertEquals(actualName,exceptedName);
	}



	

	@Given("Delete Place Payload.")
	public void delete_place_payload() throws IOException {
	resS=given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	  
	}






}
