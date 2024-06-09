package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.Converter;
import org.testng.annotations.Test;

public class UserDDTTests {

    @Test(priority = 1, dataProvider = "getAllData", dataProviderClass = DataProviders.class)
    public void testPostUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus)
    {
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
        user.setUserStatus(0);
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
    @Test(priority = 2, dataProvider = "getUserNames",dataProviderClass = DataProviders.class)
    public void testDeleteByUsername(String username) {
        Response response = UserEndPoints.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
    }
}
