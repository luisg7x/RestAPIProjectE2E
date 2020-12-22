package stepDefinitions;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

@RunWith(Cucumber.class)
public class MyStepDefinitions {

    @Given("^Add place payload$")
    public void add_place_payload() throws Throwable {
    	AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("French-IN");
		place.setWebsite("website");
		place.setName("Frontline house");
		place.setPhone_number("(+91) 983 893 3937");
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc); //expected location class, so create location class
		
		List<String> types = new ArrayList<String>();
		types.add("shop");
		types.add("school");
		place.setTypes(types); //expected a list
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		

		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
    }

    @When("^User calls \"([^\"]*)\" Api with Post http request$")
    public void user_calls_something_api_with_post_http_request(String strArg1) throws Throwable {
      
    }

    @Then("^The API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code_200() throws Throwable {
      
    }

    @And("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
    public void something_in_response_is_something(String strArg1, String strArg2) throws Throwable {
      
    }

}
