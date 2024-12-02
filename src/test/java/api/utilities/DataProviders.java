package api.utilities;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

import api.payload.Pet;

public class DataProviders {

	@DataProvider(name = "User Data")
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

	@DataProvider(name = "Pet Data")
    public Object[][] providePetData() throws IOException {
        Excel excel = new Excel("\\src\\test\\resources\\TestData\\PetStore.xlsx");
        Object[][] petList = excel.getPetData("PetData");
        return petList;
    }

	@DataProvider(name = "Order Data")
    public Object[][] provideOrderData() throws IOException {
        Excel excel = new Excel("\\src\\test\\resources\\TestData\\PetStore.xlsx");
        Object[][] petList = excel.getOrderData("OrderData");
        return petList;
    }


}
