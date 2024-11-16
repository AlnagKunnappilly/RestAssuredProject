package api.testcases;

import api.endpoints.userEndPoint;
import api.payload.user;
import api.utilities.DataProviders;
import api.utilities.ExtentManager;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager; // Import LogManager for Log4j2
import org.apache.logging.log4j.Logger;   // Import Logger for Log4j2
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.github.javafaker.Faker;

public class userTestCases {

    user userPayload;
    Faker faker;
    public static ExtentReports extent;
    public static Logger log;  // Declare the static Logger

    @BeforeTest
    public void setup() {
        // Initialize Log4j2 logger for this class
        log = LogManager.getLogger(userTestCases.class);  // Correct Logger initialization

        log.info("Started User related tests");

        // Initialize the Extent Reports
        ExtentManager.startTest("Started User related tests");
    }

    @Test(priority = 1, dataProvider = "All Data", dataProviderClass = DataProviders.class)
    public void createUser(String uId, String uName, String fName, String lName, String mail, String pass, String phNo) {
        userPayload = new user();

        userPayload.setId(Integer.parseInt(uId));
        userPayload.setUsername(uName);
        userPayload.setFirstName(fName);
        userPayload.setLastName(lName);
        userPayload.setEmail(mail);
        userPayload.setPassword(pass);
        userPayload.setPhone(phNo);
        userPayload.setUserStatus(0);

        Response responseCreateUser = userEndPoint.createUser(userPayload);

        System.out.println("Create User Response");
        responseCreateUser.then().log().all();

        Assert.assertEquals(responseCreateUser.getStatusCode(), 200);
        log.info("Created user: " + fName + " " + lName);
        ExtentManager.logInfo("Created user: " + fName + " " + lName);
    }

    @Test(priority = 2, dataProvider = "Fetch userName", dataProviderClass = DataProviders.class)
    public void getUser(String uName) {
        Response responseGetUser = userEndPoint.getUser(uName);

        System.out.println("Get User Response");
        responseGetUser.then().log().all();

        Assert.assertEquals(responseGetUser.getStatusCode(), 200);
        log.info("Fetched details of user: " + uName);
        ExtentManager.logInfo("Fetched details of user: " + uName);
    }

    @Test(priority = 3)
    public void updateUser() {
        faker = new Faker();
        userPayload.setFirstName(faker.name().firstName());

        String user = userPayload.getUsername();
        Response responseUpdateUser = userEndPoint.updateUser(userPayload, userPayload.getUsername());

        System.out.println("Update User Response");
        responseUpdateUser.then().log().all();

        Assert.assertEquals(responseUpdateUser.getStatusCode(), 200);

        Response responsePostUpdate = userEndPoint.getUser(userPayload.getUsername());
        System.out.println("After Update User Data.");
        responsePostUpdate.then().log().all();
        log.info("Updated details of user: " + user + " with firstname " + userPayload.getFirstName());

        ExtentManager.logInfo("Updated details of user: " + user + " with firstname " + userPayload.getFirstName());
    }

    @Test(priority = 4, dataProvider = "Fetch userName", dataProviderClass = DataProviders.class)
    public void deleteUser(String uName) {
        Response responseDeleteUser = userEndPoint.deleteUser(uName);

        System.out.println("Delete User Response");
        responseDeleteUser.then().log().all();

        Assert.assertEquals(responseDeleteUser.getStatusCode(), 200);
        log.info("Deleted user: " + uName);
        ExtentManager.logInfo("Deleted user: " + uName);
    }

    @AfterTest
    public void tearDown() {
        ExtentManager.endTest();
    }
}
