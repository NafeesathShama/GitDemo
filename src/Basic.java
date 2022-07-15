import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; // package for equalsTo static method - eclipse will not give auto suggestion for this packages

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.Assert;



import files.ReUsableMethods;
import files.payload;

public class Basic {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		
		//getting input body from payload
		/* String response=given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
			.body(payload.AddPlace()) */
		
		//getting input body from json file
		
				 String response=given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
					.body(new String(Files.readAllBytes(Paths.get("C:/JAVA SELENIUM/DemoProjectAPI/Add place.json"))))
					
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)
			.body("scope", equalTo("APP"))
			.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		
		//parsing the json reposnse to get perticular paramter or value - say place id in this below example
		JsonPath jpath=new JsonPath(response);
		String placeid=jpath.get("place_id");
		System.out.println("place id is " + placeid);
		
		
		//Update 
		
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
			.body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		
		//Get Place
		String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id",placeid)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
			JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
			
			String actualAddress =js1.getString("address");
			System.out.println(actualAddress);
			Assert.assertEquals(actualAddress, newAddress);
	}

}
