package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	//making static for prevent create another instance when there are 2 or 3 execution with the data set
	//and this make posible have the logs of all execeution on one file and no get replaced for the last 
	private static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		 //this if fix when there are a 2ble execution usign datasets on cucumber, that the last execeution replace the log of the last  of that exceution
		if (req==null) {
			
			PrintStream log = new PrintStream(new FileOutputStream("loggint.txt"));
			
			req = new RequestSpecBuilder().setBaseUri(getValue("baseUrl"))
					.addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			 
			 return req;
			 
		}
		
		return req;
		
	}
	
	public String getValue(String key) throws IOException {
		Properties prop = new Properties();
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\resources\\gobla.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;
	}
	
	public String getJsonPath(Response response, String key) {
		String responseST = response.asString();
		JsonPath js = new JsonPath(responseST);
		return js.get(key).toString();
	}
}
