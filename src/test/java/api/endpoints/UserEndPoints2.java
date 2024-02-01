package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;




public class UserEndPoints2 {
		
	//load properties file we can use Bundle method
	//This Method created for getting URL's from the properties file 
	static ResourceBundle getURL(){
		//this line will load that particular file
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //jus provide file name "routes is enough we dont need to pass the entier path or .properties "
		return routes;
	}
	
	public static Response createUser(User payload)
	{
		String post_url = getURL().getString("post_url");
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);
		
		return res;
	}
	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url");
		Response res = given()
			.pathParam("username", userName)
		.when()
			.get(get_url);
		
		return res;
	}
	public static Response updateUser(User payload,String userName)
	{
		String update_url=getURL().getString("update_url");
		Response res = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(update_url);
		
		return res;
	}
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url");
		Response res = given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url);
		
		return res;
	}
}
