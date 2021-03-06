package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class MyStepDefinitions extends Utils{

	private ResponseSpecification res;
	private Response response;
	
	private RequestSpecification req;
	//if you put static all test cases will refer to same variable, that means will keep the value and not will change or set to null when the next test start and the next etc 
	protected static String placeId;
	
	 @Given("^Add place payload with (.+), (.+) and (.+)$")
	   public void add_place_payload_with_and(String name, String languaje, String address)  throws Throwable {

    	req = given().spec(requestSpecification())
    			.body(TestDataBuild.addPlacePayload(name, languaje, address));
		
    }

	 @When("^User calls \"([^\"]*)\" Api with \"([^\"]*)\" http request$")
	 public void user_calls_something_api_with_something_http_request(String resource, String httpMethod) throws Throwable {
    	// REMOVED BECAUSE ON the_api_call_got_success_with_status_code_something METHOD WE ALREADY CHECK THE CODE ETC
    	//res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    					//use value of instead if/else and switch using constructor
    	APIResources resAPI = APIResources.valueOf(resource);
    	String resou = resAPI.getResource();
    	
    	if (httpMethod.equalsIgnoreCase("post")) {
    		response = req.when().post(resou);/*.then().spec(res).extract().response(); REMOVED BECAUSE ON the_api_call_got_success_with_status_code_something METHOD WE ALREADY CHECK THE CODE ETC*/
    	}else if (httpMethod.equalsIgnoreCase("get")) {
    		response = req.when().get(resou);
    	}
    		
    }

    @Then("^The API call got success with status code \"([^\"]*)\"$")
    public void the_api_call_got_success_with_status_code_something(String code) throws Throwable {
      //junit assert!, always take care of comparing values with same type, int = int and string = string
    	assertEquals(response.getStatusCode(), Integer.parseInt(code));
    	
    }

    @And("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
    public void something_in_response_is_something(String key, String expectedValor) throws Throwable {

    	assertEquals(getJsonPath(response, key), expectedValor);
    	
    }
    
    @And("^Verify place_Id created maps to (.+) using \"([^\"]*)\"$")
    public void verify_placeid_created_maps_to_using_something(String expectedName, String resourceName) throws Throwable {
    	
    	placeId = getJsonPath(response, "place_id");
    	req = given().spec(requestSpecification()).queryParam("place_id", placeId);
    	//calling method in the SAME CLASS IMPORTANT
    	user_calls_something_api_with_something_http_request(resourceName, "get");
    	
    	assertEquals(getJsonPath(response, "name"), expectedName);
    }
    
    //only this given because the rest on line (THEN, WHEN, AND) are the same as the first scenario, so is reusing the code
    @Given("^DeletePlace Payload$")
    public void deleteplace_payload() throws Throwable {
    	//no create pojo clases if the json is so basic
    	req = given().spec(requestSpecification())
        .body(TestDataBuild.deletePlacePayload(placeId));
    }

}
