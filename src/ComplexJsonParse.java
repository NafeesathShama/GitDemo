

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse 
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JsonPath js=new JsonPath(payload.CoursePrice());
		//Print No of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println("total num of courses is " + count);
		
		//Print Purchase Amount
		int pamount=js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount is " + pamount);
		
		//Print Title of the first course
		String titleFirstCourse=js.get("courses[0].title");
		 System.out.println(titleFirstCourse);
		 
		//Print All course titles and their respective Prices
		  for(int i=0;i<count;i++)
		  {
			  String courseTitles= js.get("courses["+i+"].title");
			  System.out.println("price for below course is " + js.get("courses["+i+"].price").toString());
			  
			  System.out.println("each course title is " + courseTitles);
			  
			  
		  }
		  
		//Print no of copies sold by RPA Course
		  System.out.println("Print no of copies sold by RPA Course");
		  for(int i=0;i<count;i++)
		  {
			  String courseTitles= js.get("courses["+i+"].title");
			  if(courseTitles.equalsIgnoreCase("RPA"))
			  {
				  int copies=js.get("courses["+i+"].copies");
			  System.out.println(copies);
			  break;
		  }
}
	}}
