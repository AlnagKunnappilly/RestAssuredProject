# Rest Assured Petstore API Testing Project

This is an automated testing framework for the **Petstore API**, built using **Rest Assured**, **TestNG**, and **Extent Reports** for generating detailed test execution reports.

## Prerequisites

Before you begin, ensure that you have the following installed on your local machine:

- **Java 8** or higher
- **Maven** for dependency management
- **IDE** such as Eclipse or IntelliJ IDEA
- **TestNG** testing framework
- **Rest Assured** for API testing
- **Extent Reports** for generating detailed HTML reports

## Getting Started

### 1. Clone the Repository

Start by cloning the project to your local machine:

```bash
git clone https://github.com/AlnagKunnappilly/RestAssuredProject.git
```

### 2. Install Dependencies

Navigate to the project directory and run the following Maven command to install the required dependencies:

```bash
mvn install
```

This command will download and install all dependencies, including Rest Assured, TestNG, and Extent Reports.

### 3. Project Directory Structure

Here’s a brief overview of the directory structure of the project:

```
RestAssuredPetstoreAPI/
├── src/
│   ├── test/
│   │   └── java/
│   │       └── api/
│   │           ├── endpoints/     # Endpoint descriptions
│   │           │   └── petEndPoint.java
│   │           │   └── storeEndPoint.java
│   │           │   └── userEndPoint.java
│   │           ├── payload/       # POJO classes
│   │           ├── testcases/        # Test cases for store, pet, users
│   │           │   └── petTestCases.java
│   │           │   └── storeTestCases.java
│   │           │   └── userTestCases.java
│   │           ├── utilities/  
│   │           │   └── dataProviders.java
│   │           │   └── excel.java
│   │           │   └── extentManager.java
│   │           │   └── ListenerClass.java
│   │   └── resources/       # Test resources (e.g., config files)
├── target/
│   └── extent-reports/     # Generated reports
├── pom.xml                 # Maven dependencies
├── README.md               # Project documentation
└── testng.xml              # TestNG configuration file
```

## Running the Tests

You can run the tests using **Maven** or directly through **TestNG**.

### 1. Run Tests Using Maven

To run the tests using Maven, execute the following command in the project root directory:

```bash
mvn test
```

This command will run all the tests defined in the `testng.xml` file and generate an **Extent Report** in the `target/extent-reports` folder.

### 2. Run Tests Using TestNG

Alternatively, you can run the tests directly from **TestNG** by running the following command:

```bash
testng testng.xml
```

### 3. View Test Execution Reports

After running the tests, you can find the detailed test execution report in the `target/extent-reports` folder. The report will be in HTML format, providing a detailed summary of the test execution.

## Sample Test Case

Below is an example of a simple **Rest Assured** test case for getting inventory by status in the Petstore API:

```java
@Test(priority = 2)
public void getInventoryByStatus() {
    // Sending GET request to fetch inventory details
    Response responseGetInventoryByStatus = storeEndPoint.getInventory();

    // Logging the response details
    System.out.println("Get Inventories");
    responseGetInventoryByStatus.then().log().all();

    // Assertions to validate the response
    Assert.assertEquals(responseGetInventoryByStatus.getStatusCode(), 200);
    log.info("Fetched details of inventory by status " + store.getStatus());
    ExtentManager.logInfo("Fetched details of inventory by status " + store.getStatus());
}
```

This test sends a `GET` request to the **Petstore API** to fetch inventory details and asserts that the response code is `200`. It also logs information about the inventory status.

## Endpoints Tested

### Example Petstore API Endpoints:
- **Create Pet** (`POST /pet`)
- **Get Pet by ID** (`GET /pet/{petId}`)
- **Update Pet** (`PUT /pet`)
- **Delete Pet** (`DELETE /pet/{petId}`)

You can add more endpoints and test cases as needed.

## Extent Reports Integration

This project integrates **Extent Reports** to generate detailed, interactive, and visually appealing HTML reports. Each test run generates a report with the following features:

- **Test Pass/Fail Status**: Indicates whether the tests passed or failed.
- **Detailed Logs**: Displays detailed request/response data and any errors encountered during execution.
- **Screenshots**: If configured, screenshots of API responses can be added for failed tests (this requires additional setup).
- **Execution Timeline**: Visual timeline of the tests run, including execution details.

### Example of Configuring Extent Reports:

The `ExtentReports` configuration is defined in the `ExtentManager` class, which initializes the report before the tests are run.

Here’s a snippet of how the reports are configured:

```java
public class ExtentManager {

    private static ExtentReports extent;
    
    public static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(System.getProperty("user.dir") + "/target/extent-reports/extent-report.html", true);
            extent.loadConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
        }
        return extent;
    }
}
```

### TestNG Listeners

TestNG listeners are used to integrate **Extent Reports** with TestNG. You can add listeners to your `testng.xml` file to capture test results.

Here is an example of a listener configuration:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="Petstore API Tests">
    <test thread-count="5" name="Test">
        <classes>
            <class name="api.testcases.userTestCases" />
            <class name="api.testcases.petTestCases" />
            <class name="api.testcases.storeTestCases" />
        </classes>
    </test>
</suite>
```

## Contributing

We welcome contributions to improve the project! To contribute:

1. Fork the repository
2. Create a new branch (`git checkout -b feature/your-feature`)
3. Make your changes
4. Commit your changes (`git commit -m 'Add new feature'`)
5. Push to the branch (`git push origin feature/your-feature`)
6. Create a pull request

## License

This project is licensed under the **MIT License**.

---

### Customization Notes:

1. **Logging**: In the `Sample Test Case`, I added `log.info` and `ExtentManager.logInfo` for logging purposes. Make sure you have configured a logging framework (like Log4j or SLF4J) to log messages.
2. **Test Case Priorities**: In the sample test case, I used `@Test(priority = 2)`. TestNG allows you to set priorities for test methods, so the order of execution can be controlled.
3. **Extent Configurations**: The `extent-config.xml` file can be customized for changing the look and feel of the Extent report, such as setting the theme, adding custom logos, etc.

