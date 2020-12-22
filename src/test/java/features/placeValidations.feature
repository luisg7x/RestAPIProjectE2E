Feature: Validating place API's

Scenario: Verify if place is beaing succesfully added using AddPlaceAPI
Given Add place payload
When User calls "AddPlace" Api with Post http request
Then The API call got success with status code 200
And "status" in response is "OK"
And "scope" in response is "APP"

#doble "" in "AddPlace" on AddPLace for reuse statement, because can use for Delete, Update, get 
#same for status and ok, because you can expect mayme get a id with 200 as valor on response