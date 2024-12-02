package api.endpoints;

import api.payload.User;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

public class userEndPoint {

	public static ResourceBundle getURLS() {
		ResourceBundle routes = ResourceBundle.getBundle("Routes");
		return routes;
	}

	public static Response createUser(User payload) {

		Response responsePost = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(getURLS().getString("userPostURL"));

		return responsePost;
	}

	public static Response getUser(String userName) {
		Response responseGet = given().accept(ContentType.JSON).pathParam("username", userName)

				.when().get(getURLS().getString("userGetURL"));
		return responseGet;
	}

	public static Response updateUser(User requestBody, String userName) {
		Response responsePut = given().accept(ContentType.JSON).with().pathParam("username", userName)
				.contentType(ContentType.JSON).body(requestBody)

				.when().put(getURLS().getString("userPutURL"));
		return responsePut;
	}

	public static Response deleteUser(String userName) {
		Response responseDel = given().accept(ContentType.JSON).with().pathParam("username", userName)

				.when().delete(getURLS().getString("userDeleteURL"));
		return responseDel;
	}

}
