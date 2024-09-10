package stepDefinition;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	//@Before //precondition
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		//Write a code that will give place id
		//execute this code only when place will null
	
		PlaceValidations place=new PlaceValidations();
		if(PlaceValidations .placeId==null) {
			place.add_place_payload_with("Shetty", "French","Asia");
			place.user_calls_with_post_http_request("addPlaceAPI", "POST");
			place.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
		}
	
	

}
