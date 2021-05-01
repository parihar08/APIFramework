Feature: Validating Place APIs

#Scenario: Verify if place is being successfully added using AddPlace API
	
#	Given Add Place Payload
#	When User calls "AddPlace" API with POST HTTP request
#	Then The API call is success with status code 200
#	And The "status" in response body is "OK"
#	And The "scope" in response body is "APP"

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlace API
	
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When User calls "AddPlaceAPI" with "POST" HTTP request
	Then The API call is success with status code 200
	And The "status" in response body is "OK"
	And The "scope" in response body is "APP"
	And Verify place_id created maps to "<name>" using "GetPlaceAPI"

Examples:
		|name						|	language		|address							|
		|KichenerHouse		|	English		|Kitchener, Ontario	|
		|TorontoHouse		|	French			|Etobicoke, Ontario	|

@DeletePlace	
Scenario: Verify if Delete Place functionality is working
	Given Delete Place Payload
	When User calls "DeletePlaceAPI" with "POST" HTTP request
	Then The API call is success with status code 200
#	And The "status" in response body is "OK"

	
	
	

