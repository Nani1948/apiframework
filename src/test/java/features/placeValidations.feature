Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being sucucessfully added using AddPlaceAPI.
         
         Given Add Place Payload with "<name>" "<language>" "<address>".
         When User calls "addPlaceAPI" with "POST" http request.
         Then API call got success with status code 200.
         And  "status" in response body is "OK".
         And "scope" in response body is "APP".
         And Verify place_Id created maps to "<name>" using "getPlaceAPI".
         
Examples:
         |name|language|address|
         |AAhouse|English|World cross Center|
           |Ahouse|English|World cross|        
         

 @DeletePlace        
 Scenario: Verify the delete place functionality is working
          Given Delete Place Payload.
          When User calls "deletePlaceAPI" with "POST" http request.  
          Then API call got success with status code 200.
          And "status" in response body is "OK".