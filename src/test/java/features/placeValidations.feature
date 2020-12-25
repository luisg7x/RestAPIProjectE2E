Feature: Validating place API's
#tagging the test with the @, go to Testrunner and see how run only one test etc
@AddPlace
Scenario Outline: Verify if place is beaing succesfully added using AddPlaceAPI
Given Add place payload with <name>, <languaje> and <address>
When User calls "addPlaceAPI" Api with "Post" http request
Then The API call got success with status code "200"
And "status" in response is "OK"
And "scope" in response is "APP"
And Verify place_Id created maps to <name> using "getPlaceAPI"

#doble "" in "AddPlace" on AddPLace for reuse statement, because can use for Delete, Update, get 
#same for status and ok, because you can expect maybe get a id with 200 as valor on response.. always try reusing code if is posible

#add outline on scenation for use the Examples
Examples:
|name	| languaje	|	address 			|
|Axo	| English-EN| World cross center	|
|Ara	| Spanish-ES| Camp-Nou				|
|Kai	| Spanish-ES| Madrid				|		

@DeletePlace
Scenario: Verify if delete place functionality is working
Given DeletePlace Payload
When  User calls "deletePlaceAPI" Api with "Post" http request
Then The API call got success with status code "200"
And "status" in response is "OK"