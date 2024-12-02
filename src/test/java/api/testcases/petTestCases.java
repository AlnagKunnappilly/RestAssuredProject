package api.testcases;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.github.javafaker.Faker;

import api.endpoints.petEndPoint;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import api.utilities.DataProviders;
import api.utilities.ExtentManager;
import io.restassured.response.Response;

public class petTestCases {
	public static ExtentReports extent;
	public static Logger log;
	public Pet pet;
	Faker faker;
	public static List<BigInteger> petIdList = new ArrayList<>();
	public static BigInteger[] petIds;
	public static BigInteger petID;
	
	@Test(priority = 0) 
	public void extentConfigurePet() {
		log = LogManager.getLogger(petTestCases.class);
		log.info("Started Pet related tests");
		ExtentManager.startTest("Started Pet related tests");
	}
	@Test(priority = 1, dataProvider = "Pet Data", dataProviderClass = DataProviders.class)
	public void createPet(int petId, Category category, String petName, List<String> photoUrls, List<Tag> tags,
			String status) {
		pet = new Pet();
		pet.setPetId(BigInteger.valueOf(petId));
		pet.setCategory(category);
		pet.setPetName(petName);
		pet.setPhotoUrls(photoUrls);
		pet.setTags(tags);
		pet.setStatus(status);

		Response responseCreatePet = petEndPoint.createPet(pet);
		System.out.println("Create Pet Response");
		responseCreatePet.then().log().all();

		Assert.assertEquals(responseCreatePet.getStatusCode(), 200);

		String petIDFromResponse = responseCreatePet.jsonPath().getString("id");
		petID = new BigInteger(petIDFromResponse);
		petIdList.add(petID);
		System.out.println("Pet ID List: " + petIdList);

		log.info("Created pet: " + pet.getPetName());
		ExtentManager.logInfo("Created pet: " + pet.getPetName());
	}

	@Test(priority = 2)
	public void getPet() {
		if (petIdList.isEmpty()) {
			System.out.println("No pet IDs to retrieve");
			return;
		}
		petIds = petIdList.toArray(new BigInteger[0]);
		System.out.println(Arrays.deepToString(petIds));
		for (int i = 0; i < petIds.length; i++) {
			petID = petIds[i];
			Response responseGetPet = petEndPoint.getPet(petID);
			System.out.println("Get Pet Response");
			responseGetPet.then().log().all();

			Assert.assertEquals(responseGetPet.getStatusCode(), 200);
			log.info("Fetched details of pet: " + pet.getPetName());
			ExtentManager.logInfo("Fetched details of pet: " + pet.getPetName());
		}
	}

	@Test(priority = 3)
	public void getPetByStatus() {
		String status = "available";

		Response responseGetPetByStatus = petEndPoint.getPetByStatus(status);
		System.out.println("Get pet details by status");

		responseGetPetByStatus.then().log().all();
		Assert.assertEquals(responseGetPetByStatus.getStatusCode(), 200);
		log.info("Fetched details of pet by status : " + pet.getPetName());
		ExtentManager.logInfo("Fetched details of pet by status :  " + pet.getPetName());

	}

	@AfterTest
	public void tearDown() {
		ExtentManager.endTest();
	}
}
