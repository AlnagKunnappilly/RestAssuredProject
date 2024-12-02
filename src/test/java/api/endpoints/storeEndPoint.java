package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class storeEndPoint {
	public static ResourceBundle getURLS() {
		ResourceBundle routes = ResourceBundle.getBundle("Routes");
		return routes;
	}

	public static Response getInventory() {

		Response responseGetInventory = given().contentType(ContentType.JSON)

				.when().get(getURLS().getString("storeGetURL"));

		return responseGetInventory;
	}

	public static Response postOrder(Store payload) {

		Response responsePost = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)

				.when().post(getURLS().getString("storePostURL"));

		return responsePost;
	}

	public static Response getpurchaseById(int orderId) {

		Response responseGetpurchaseById = given().accept(ContentType.JSON).with().pathParam("orderId", orderId)

				.when().get(getURLS().getString("storeGetPurchaseURL"));

		return responseGetpurchaseById;
	}
	
	public static Response deleteOrder(int orderId) {
		Response responseDel = given().accept(ContentType.JSON).with().pathParam("orderId", orderId)

				.when().delete(getURLS().getString("storeDeleteURL"));
		return responseDel;
	}

}
