package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static ResourceBundle getUrl() {
        //String post_url = Routes.getURLFromProperties().getString("post_url");
        //System.out.println("Properties URL " + post_url);

        ResourceBundle bundle = ResourceBundle.getBundle("routes");


        return bundle;
    }

    public static Response createUser(User use) {
        System.out.println("URL" + getUrl().getString("post_url"));

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(use)
                .when()
                .post(Routes.post_url);
        return response;
    }

    public static Response getUser(String username) {

        Response response = given()
                .pathParam("username", username)
                .when()
                .get(Routes.get_url);
        return response;
    }

    public static Response updateUser(String username, User user) {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .pathParam("username", username)
                .when()
                .put(Routes.update_url);
        return response;
    }

    public static Response deleteUser(String username) {
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(Routes.delete_url);
        return response;
    }


}
