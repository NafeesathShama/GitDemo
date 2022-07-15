import com.aventstack.extentreports.gherkin.model.Given;

import POJO.AddPlace;
import POJO.Location;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;

public class SpecBuilderTest 
{
	public static void main(String[] args) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddPlace p=new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		
		List<String> l=new ArrayList<String>();
		l.add("Shoerack");
		l.add("Shop");
		p.setTypes(l);
		
		Location l1 =new Location();
		l1.setLat(-38.383494);
		l1.setLng(33.427362);
		p.setLocation(l1);
		
		 RequestSpecification reqspec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		 
		 ResponseSpecification resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		 
		 RequestSpecification res=given().spec(reqspec).body(p);
		 
		 Response response= res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();

String responseString=response.asString();
System.out.println(responseString);
}
}