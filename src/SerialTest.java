import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;


import POJO.AddPlace;
import POJO.Location;;

public class SerialTest 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
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
		
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response res=given().log().all().queryParam("key","qaclick123").body(p)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		String responseString=res.asString();
		System.out.println(responseString);
		
		
	}
}
