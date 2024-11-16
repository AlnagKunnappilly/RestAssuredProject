package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "All Data")
	public Object[][] allDataProvider() throws IOException {

		Excel excel = new Excel("\\src\\test\\resources\\TestData\\PetStore.xlsx");
		int row = excel.getRowCount("UserData");
		int col = excel.getCellCount("UserData", row);
		Object[][] userDetails = excel.getCellData("UserData", row, col);
		return userDetails;
	}
	
	@DataProvider(name = "Fetch userName")
	public String[] fetchUsrName() throws IOException {

		Excel excel = new Excel("\\src\\test\\resources\\TestData\\PetStore.xlsx");
		String[] userNames = excel.fetchSecCol("UserData");
		return userNames;
	}

}
