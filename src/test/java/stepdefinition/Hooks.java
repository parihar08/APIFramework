package stepdefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{
		//Write code that will give us place_id
		//Execute this code only when place_id is null
		
		StepDefinition m = new StepDefinition();
		
		if(StepDefinition.place_id == null) 
		{
			m.add_Place_Payload_with("Sandeep Parihar", "Punjabi", "56 Millview Cres");
			m.user_calls_api_with_post_http_request("AddPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Sandeep Parihar", "GetPlaceAPI");
		}
	}

}
