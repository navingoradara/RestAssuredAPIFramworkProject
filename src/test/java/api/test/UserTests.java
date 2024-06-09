package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User user;
    public Logger logger;
    @BeforeClass
    public void setupData() {
        faker = new Faker();
        user = new User();
        user.setId(faker.idNumber().hashCode());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password(5, 10));
        user.setPhone(faker.phoneNumber().cellPhone());

        //loggers

        logger= LogManager.getLogger(this.getClass());
    }
    @Test(priority = 1)
    public void testPostUser() {
        logger.info("*** create a user***");
        Response response = UserEndPoints.createUser(user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        logger.info("*** end a user***");
    }
    @Test(priority = 2)
    public void testGetUserByName() {
        logger.info("*** get user start ***");
        Response response = UserEndPoints.getUser(this.user.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        logger.info("*** get user end ***");

    }
    @Test(priority = 3)
    public void testUpdateUserbyName() {
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().emailAddress());
        Response response = UserEndPoints.updateUser(this.user.getUsername(), user);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        //check data is updated
        Response responseUpdate = UserEndPoints.getUser(this.user.getUsername());
        responseUpdate.then().log().all();
        Assert.assertEquals(responseUpdate.getStatusCode(), HttpStatus.SC_OK);

    }
    @Test(priority = 4)
    public void testDeleteByUsername() {
        Response response = UserEndPoints.deleteUser(this.user.getUsername());
        Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
    }

}
