package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {
	//this methods is cause of if you only want run the test deletePlace, you need a placeId to do that, so as only this will run and the addPlace dont will, so we need to generate a placeId using the methods of stepDefinition
			//write the tag of the test in .feature file
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		//execute this code only when placeId is null
		//write a code taht will give you placeId
		
		//calling methods with this instance for generate a placeId
		MyStepDefinitions ms = new MyStepDefinitions();
		//verifing if placeId is null
		//for remove the warning of if(ms.placeId==null) use the class name directly:
		if(MyStepDefinitions.placeId==null) {
			ms.add_place_payload_with_and("Calito", "Spanish-NR", "America");
			ms.user_calls_something_api_with_something_http_request("addPlaceAPI", "POST");
			ms.verify_placeid_created_maps_to_using_something("Calito", "getPlaceAPI");
		}
	}
}
