package ProjectWork.RestAssured;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UserCreateLogInDelete {
	
	@Test(enabled=true)
	public void createUser(ITestContext var) {
		
		JSONObject obj = new JSONObject();
	
		obj.put("username", "pravin");
		obj.put("firstName", "pravinfn");
		obj.put("lastName", "pravinln");
		obj.put("email", "test@mail.com");
		obj.put("password", "testpwd");
		obj.put("phone", "123456");
		obj.put("userStatus", 0);
	
		
		var.setAttribute("username","pravin");
		var.setAttribute("password","testpwd");
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		//create user
		RestAssured.
		given().
		contentType(ContentType.JSON).body(obj.toJSONString()).
		when().post("/user").then().statusCode(200).log().all();
		
	
		
	}
		
	@Test(enabled=true)
	public void modifyuser(ITestContext var) {
		
		
		String username= var.getAttribute("username").toString();
		
		JSONObject obj = new JSONObject();
	
		obj.put("username", "pravin");
		obj.put("firstName", "pravinfn1");
		obj.put("lastName", "pravinln1");
		obj.put("email", "test@mail.com");
		obj.put("password", "testpwd");
		obj.put("phone", "123456");
		obj.put("userStatus", 0);
		
		//modifyuser
		RestAssured.
		given().
		contentType(ContentType.JSON).body(obj.toString()).
		when().put("/user/"+username).then().statusCode(200).log().all();
		
	
		 }
	
	
		
		
	@Test(enabled=true)
	public void login(ITestContext var) {
		
		String username= var.getAttribute("username").toString();
		String password= var.getAttribute("password").toString();
		
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		//login
		RestAssured.
		given().
		contentType(ContentType.JSON).queryParam("username", username).queryParam("password", password).
		when().get("/user/login").then().statusCode(200).log().all();
	}
	
	@Test(enabled=true)
	public void logout() {
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		//logout
		RestAssured.
		given().get("/user/logout").then().statusCode(200).log().all();
	}
	
	@Test(enabled=true)
	public void delete(ITestContext var) {
		
		String username= var.getAttribute("username").toString();
				
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		
		//delete
		RestAssured.
		given().
		contentType(ContentType.JSON).
		when().delete("/user/"+username).then().statusCode(200).log().all();
		
	}	
			

}
