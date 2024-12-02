package api.testcases;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import api.endpoints.storeEndPoint;
import api.payload.Store;
import api.utilities.DataProviders;
import api.utilities.ExtentManager;
import io.restassured.response.Response;

public class StoreTestCases {
	public static ExtentReports extent;
	public static Logger log;
	public Store store;
	public Random random;
	public static int orderID;
	
	@Test(priority = 0) 
	public void extentConfigureStore()
	{
		log = LogManager.getLogger(StoreTestCases.class);
		log.info("Started order related tests");
		ExtentManager.startTest("Started order related tests");
	}

	@Test(priority = 1, dataProvider = "Order Data", dataProviderClass = DataProviders.class)
	public void createOrder(int orderId, int petId, int quantity, String shipDate, String status, Boolean complete) {
		log.info("Started Order related tests");
		store = new Store(orderId, petId, quantity, shipDate, status, complete);
		store.setOrderId(orderId);
		store.setPetId(petId);
		store.setQuantity(quantity);
		store.setShipDate(shipDate);
		store.setStatus(status);
		store.setComplete(complete);
		Response responseCreateOrder = storeEndPoint.postOrder(store);
		System.out.println("Created orders");
		responseCreateOrder.then().log().all();
		Assert.assertEquals(responseCreateOrder.getStatusCode(), 200);
		log.info("Created order : " + store.getOrderId() + " for pet with status " + store.getStatus());
		ExtentManager.logInfo("Created order : " + store.getOrderId() + " for pet with status " + store.getStatus());
	}

	@Test(priority = 2)
	public void getInventoryByStatus() {
		Response responseGetInventoryByStatus = storeEndPoint.getInventory();

		System.out.println("Get Inventories");
		responseGetInventoryByStatus.then().log().all();

		Assert.assertEquals(responseGetInventoryByStatus.getStatusCode(), 200);
		log.info("Fetched details of inventory by status " + store.getStatus());
		ExtentManager.logInfo("Fetched details of inventory by status " + store.getStatus());
	}

	@Test(priority = 3)
	public void getOrderById() {

		random = new Random();
		orderID = random.nextInt(9) + 1;
		Response responseGetOrderById = storeEndPoint.getpurchaseById(orderID);

		System.out.println("Get orders by ID ");
		responseGetOrderById.then().log().all();

		Assert.assertEquals(responseGetOrderById.getStatusCode(), 200);
		log.info("Fetched details of order by ID  " + orderID);
		ExtentManager.logInfo("Fetched details of order by ID " + orderID);
	}

	@Test(priority = 4)
	public void deleteOrderById() {
		Response responseDeleteOrderById = storeEndPoint.deleteOrder(orderID);

		System.out.println("Delete order by ID ");
		responseDeleteOrderById.then().log().all();

		Assert.assertEquals(responseDeleteOrderById.getStatusCode(), 200);
		log.info("Deleted the details of order by ID  " + orderID);
		ExtentManager.logInfo("Deleted the details of order by ID " + orderID);
	}

	@AfterTest
	public void tearDown() {
		ExtentManager.endTest();
	}

}
