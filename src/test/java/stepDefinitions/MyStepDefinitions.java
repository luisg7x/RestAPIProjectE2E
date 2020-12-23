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
import resources.TestDataBuild;
import resources.Utils;

@RunWith(Cucumber.class)
public class MyStepDefinitions extends Utils{

	private ResponseSpecification res;
	private Response response;
	
	private RequestSpecification req;
	
    @Given("^Add place payload$")
    public void add_place_payload() throws Throwable {

    	req = given().spec(requestSpecification())
    			.body(TestDataBuild.addPlacePayload());
		
    }

    @When("^User calls \"([^\"]*)\" Api with Post http request$")
    public void user_calls_something_api_with_post_http_request(String strArg1) throws Throwable {
    	
    	res = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
    	
    	response = req.when().post("/maps/api/place/add/json").then().spec(res) .extract().response();
    	
    }

    @Then("^The API call got success with status code \"([^\"]*)\"$")
    public void the_api_call_got_success_with_status_code_something(String code) throws Throwable {
      //junit assert!, always take care of comparing values with same type, int = int and string = string
    	assertEquals(response.getStatusCode(), Integer.parseInt(code));
    	
    }

    @And("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
    public void something_in_response_is_something(String key, String expectedValor) throws Throwable {
    	
    	String responseST = response.asString();
    	JsonPath js = new JsonPath(responseST);
    	
    	assertEquals(js.get(key).toString(), expectedValor);
    	
    }

}
