package stepdefinition;

import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

import static io.restassured.RestAssured.given;
import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;
import io.cucumber.java.en.Given;

public class StepDefinition extends Utilities {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
//		@Given("Add Place Payload")
//		public void add_place_payload() throws IOException {
	
//			res=given().spec(requestSpecification())
//			.body(data.addPlacePayload());
//			}
			
		@Given("Add Place Payload with {string} {string} {string}")
			public void add_Place_Payload_with(String name, String language, String address) throws IOException	{
			  	
			res=given().spec(requestSpecification())
					   .body(data.addPlacePayload(name, language, address));
		}
		
//		@When("User calls {string} with HTTP request")
//		public void user_calls_api_with_post_http_request(String resource) {
//			
//			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//			response =res.when().post("/maps/api/place/add/json").
//					then().spec(resspec).extract().response();
//		}
		
		@When("User calls {string} with {string} HTTP request")
		public void user_calls_api_with_post_http_request(String resource, String httpMethod) {
			
			APIResources resourceAPI = APIResources.valueOf(resource);	
			
			resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			
			if(httpMethod.equalsIgnoreCase("POST")){
			response =res.when().post(resourceAPI.getResource());
			}
			
			else if(httpMethod.equalsIgnoreCase("GET")){
				response =res.when().get(resourceAPI.getResource());
				}
			
			else if(httpMethod.equalsIgnoreCase("DELETE")){
				response =res.when().delete(resourceAPI.getResource());
				}
		}
		
		@Then("The API call is success with status code {int}")
		public void the_api_call_is_success_with_status_code(Integer int1) {
		    
			
			//response = response.then().spec(resspec).extract().response();
			//response.then().assertThat().statusCode(200).extract().response();	 
			assertEquals(response.getStatusCode(),200);
		}
		
		@Then("The {string} in response body is {string}")
		public void the_in_response_body_is(String keyValue, String expectedValue) {
			
//			String resp = response.asString();
//			js = new JsonPath(resp);
//			assertEquals(js.get(keyValue).toString(),expectedValue);
			
			assertEquals(getJsonPath(response,keyValue),expectedValue);
		    
		}
		
		//Get Place
		@Then("Verify place_id created maps to {string} using {string}")
		public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException  {
			
			place_id = getJsonPath(response,"place_id");
			res=given().spec(requestSpecification()).queryParam("place_id", place_id);
			
			user_calls_api_with_post_http_request(resource, "GET");
			
			String actualName = getJsonPath(response,"name");
			assertEquals(actualName,expectedName);
		    
		}
		
		//Delete Place
		
		@Given("Delete Place Payload")
		public void delete_place_payload() throws IOException {
	
			res=given().spec(requestSpecification())
			.body(data.deletePayLoad(place_id));
		}



}
