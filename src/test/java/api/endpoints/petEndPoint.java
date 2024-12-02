package api.endpoints;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.math.BigInteger;
import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class petEndPoint {

	public static ResourceBundle getURLS() {
		ResourceBundle routes = ResourceBundle.getBundle("Routes");
		return routes;
	}

	public static Response createPet(Pet payload) {

		Response responsePost = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(getURLS().getString("petCreateURL"));

		return responsePost;
	}

	public static Response getPet(BigInteger petID) {

		Response responseGet = given().accept(ContentType.JSON).with().pathParam("petId", petID)

				.when().get(getURLS().getString("petGetURL"));

		return responseGet;
	}

	public static Response getPetByStatus(String status) {

		Response responseGetStatus = given().accept(ContentType.JSON).with().queryParam("status", status)

				.when().get(getURLS().getString("petGetStatusURL"));

		return responseGetStatus;
	}

}
