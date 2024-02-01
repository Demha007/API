package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker fk; 
	User userPayload;
	
	
	public Logger logger;
	@BeforeClass
	public void setup() {  //setting up the data using pojo
		fk = new Faker();
		userPayload = new User();
		
		userPayload.setId(fk.idNumber().hashCode());
		userPayload.setUsername(fk.name().username());
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		userPayload.setPassword(fk.internet().password(5, 10));
		userPayload.setPhone(fk.phoneNumber().cellPhone());
		
		//initiating logs
		logger = LogManager.getLogger(this.getClass());
		logger.debug("Debugging...........");
	}
	@Test(priority=1)
	public void testPostUser() {
		
		logger.info("******************Creating User *****************");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(),200);
		
		logger.info("*******************User Created Successfully **********");
	}
	
	@Test(priority=2)
	public void testGetUserByName() {
		logger.info("****************** Reading User Info *****************");
		Response res= UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();
		
		Assert.assertEquals(res.getStatusCode(), 200);
		logger.info("****************** User Info is Dislayed *****************");
	}
	@Test(priority=3)
	public void testUpdateUserByName() {
		logger.info("******************Updating  User *****************");
		//update data using payload
		userPayload.setFirstName(fk.name().firstName());
		userPayload.setLastName(fk.name().lastName());
		userPayload.setEmail(fk.internet().safeEmailAddress());
		
		Response res = UserEndPoints.updateUser(userPayload,this.userPayload.getUsername());
		res.then().log().body();
//		res.then().log().body().statusCode(200); this is also correct way to do the statuscode
		Assert.assertEquals(res.getStatusCode(),200);
		
		//Checking data after updating
		Response resAfterUpdate = UserEndPoints.readUser(this.userPayload.getUsername());
		
		Assert.assertEquals(resAfterUpdate.getStatusCode(),200);
		
		logger.info("****************** User is Updated*****************");
	}
	
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("******************Deleting User *****************");
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("****************** User is Deleted *****************");
	}
}
