package com.placevalidation.test;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.placevalidations.pojo.AddPlace;
import com.placevalidations.pojo.Location;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;



public class SpecBuilder {
	public static void main(String []args) {
	   RestAssured.baseURI="https://rahulshettyacademy.com";

		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		/*Response res=given().log().all().queryParam("key", "qaclick123")
				.body(p)
				.when().post("/maps/api/place/add/json").
				then().assertThat().statusCode(200).extract().response();

				String responseString=res.asString();
				System.out.println(responseString);*/
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();
		RequestSpecification  res=given().spec(req)
				.body(p);
		 
		ResponseSpecification resspec =new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectContentType(ContentType.JSON).build();	 
		Response response =res.when().post("/maps/api/place/add/json").
				then().spec(resspec ).extract().response();		
				//or 
		
				//Response response =res.when().post("/maps/api/place/add/json").
				//then().assertThat().statusCode(200).extract().response();
                  
				String responseString=response.asString();
				System.out.println(responseString);

	}

}
