package resources;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static RequestSpecification req=null;
	PrintStream log=null;
	PropertyUtils readDataFromFile=new PropertyUtils();
	JsonPath js=null;
	
	public   RequestSpecification requestSpecification() throws IOException {
		if(req==null) {
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
	     req=new RequestSpecBuilder().setBaseUri(readDataFromFile.getValueFromFile("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
	     return req;
		}
		return req;
	}
	
    public String getJsonPath(Response response,String key) {
    	String respS=response.asString();
    	js=new JsonPath(respS);
    	return js.get(key).toString();
    	
    	
    }

}
