import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class OAuthTest 
{

	public static void main(String[] args)
	{
		
		String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
		
		/*
		String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
	 System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("fdfd");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("fxfe");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url=driver.getCurrentUrl();
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		*/
		
		
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWj8z02pacgiZIqHzEA_2ZCLnwMOT1pO4RDcBynTrB_DuD-vnYZnOjsrnI9dkUHzHw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
		//need to change the above url for every run it expires, else test won pass
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println("Auth Code is " + code);
		
		
		String AccessTokenResponse=
				given()
				.urlEncodingEnabled(false)
				.queryParams("code", code)
			.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
			.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
			.queryParams("grant_type","authorization_code")
			.when().log().all()
			.post("https://www.googleapis.com/oauth2/v4/token")
			.asString();
		System.out.println("Access token response 1 is " + AccessTokenResponse);
		
		
		JsonPath js=new JsonPath(AccessTokenResponse);
		String AccessToken=js.getString("access_token");
		System.out.println("Access token response is " + AccessToken);

		
		
		/*
		String response=given().queryParam("access_token", AccessToken)
		.when().log().all().
		get("https://rahulshettyacademy.com/getCourse.php?").asString();
		System.out.println(response);
		*/
		
		GetCourse gc=given().queryParam("access_token", AccessToken).expect()
				.defaultParser(Parser.JSON)
				.when().
				get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		
		System.out.println("linkdin URL is" + gc.getLinkedIn());
		System.out.println("instructor is " + gc.getInstructor());
		
		String CourseTitle1=gc.getCourses().getApi().get(1).getCourseTitle();
		System.out.println(CourseTitle1);
		
		List<Api> apiCourses=gc.getCourses().getApi();
		for (int i = 0; i < apiCourses.size(); i++) 
		{
			if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(apiCourses.get(i).getPrice());
			}
		}
		
		ArrayList<String> a=new ArrayList<>();
		List<WebAutomation> coursesOfAUtomations=gc.getCourses().getWebAutomation();
		for (int j = 0; j < coursesOfAUtomations.size(); j++) 
		{
			a.add((coursesOfAUtomations.get(j).getCourseTitle()));
		}
		
		List<String> expList=Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expList));
		
				
	}
}
