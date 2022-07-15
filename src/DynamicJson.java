import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
import files.*;


public class DynamicJson
{

@Test(dataProvider="BooksData")
public void addBook(String isbn,String aisle)
{
RestAssured.baseURI="http://216.10.245.166";
String resp=given().header("Content-Type","application/json").
body(payload.AddBook(isbn,aisle)).
when().
post("/Library/Addbook.php").
then().assertThat().statusCode(200).extract().response().asString();

JsonPath js= ReUsableMethods.rawToJson(resp);
   String id=js.getString("ID");
   System.out.println(id);
}


@DataProvider(name="BooksData")
private Object[][] getData() 
{
	return new Object[][] {{"book2","02"},{"book3","03"},{"book4","04"},{"book5","05"}};
			
}
}