package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String userId, String un,String fn,String ln,String useremail,String pwd,String ph){ 
		User up = new User();
		
		up.setId(Integer.parseInt(userId));
		up.setUsername(un);
		up.setFirstName(fn);
		up.setLastName(ln);
		up.setEmail(useremail);
		up.setPassword(pwd);
		up.setPhone(ph);
		
		Response res = UserEndPoints.createUser(up);
		res.then().log().body();
		
		Assert.assertEquals(res.getStatusCode(),200);
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		Response res = UserEndPoints.deleteUser(userName);
		
		Assert.assertEquals(res.getStatusCode(), 200);
		
		
	}
}
