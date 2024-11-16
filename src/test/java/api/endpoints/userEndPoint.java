package api.endpoints;

import api.payload.user;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Properties;
import java.util.ResourceBundle;

public class userEndPoint {

	public static ResourceBundle getURLS() {
		ResourceBundle routes=ResourceBundle.getBundle("Routes");
		return routes;
	}

	public static Response createUser(user payload) {

		Response responsePost = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(getURLS().getString("postURL"));

		return responsePost;
	}

	public static Response getUser(String userName) {
		Response responseGet = given().accept(ContentType.JSON).pathParam("username", userName)

				.when().get(getURLS().getString("getURL"));
		return responseGet;
	}

	public static Response updateUser(user requestBody, String userName) {
		Response responsePut = given().accept(ContentType.JSON).with().pathParam("username", userName)
				.contentType(ContentType.JSON).body(requestBody)

				.when().put(getURLS().getString("putURL"));
		return responsePut;
	}

	public static Response deleteUser(String userName) {
		Response responsePut = given().accept(ContentType.JSON).with().pathParam("username", userName)

				.when().delete(getURLS().getString("deleteURL"));
		return responsePut;
	}

}
