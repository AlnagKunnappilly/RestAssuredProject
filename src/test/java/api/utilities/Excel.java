package api.utilities;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.payload.*;
import api.payload.Tag;

import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Excel {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public Excel(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}

	public Object[][] getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		int totalRows = sheet.getPhysicalNumberOfRows();

		String[][] data = new String[totalRows - 1][colNum];
		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < totalRows; i++) {
			row = sheet.getRow(i);

			if (row != null) {
				for (int j = 0; j < colNum; j++) {
					cell = row.getCell(j);
					if (cell != null) {

						data[i - 1][j] = formatter.formatCellValue(cell);
					} else {
						data[i - 1][j] = "";
					}
				}
			}
		}

		workbook.close();
		fi.close();

		return data;
	}

	public String[] fetchSecCol(String sheetName) throws IOException {
		fi = new FileInputStream(System.getProperty("user.dir") + path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int totalRows = sheet.getPhysicalNumberOfRows();

		String[] colSecond = new String[totalRows - 1];

		DataFormatter formatter = new DataFormatter();

		for (int i = 1; i < totalRows; i++) {
			row = sheet.getRow(i);

			if (row != null) {
				cell = row.getCell(1);

				if (cell != null) {
					colSecond[i - 1] = formatter.formatCellValue(cell);
				} else {
					colSecond[i - 1] = "";
				}
			}
		}

		workbook.close();
		fi.close();

		return colSecond;
	}

	
	public Object[][] getPetData(String sheetName) throws IOException {
	    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + path);
	    workbook = new XSSFWorkbook(fileInputStream);
	    sheet = workbook.getSheet(sheetName);
	    
	    int rowCount = sheet.getPhysicalNumberOfRows();
	    List<Object[]> petDataList = new ArrayList<>();
	    
	    for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
	        Row row = sheet.getRow(rowIndex);
	        
	        if (row != null) {
	            int categoryId = (int) row.getCell(0).getNumericCellValue();
	            String categoryName = row.getCell(1).getStringCellValue();
	            String petName = row.getCell(2).getStringCellValue();
	            String photoUrl = row.getCell(3).getStringCellValue();
	            List<String> photoUrls = List.of(photoUrl.split(",\\s*"));
	            
	            List<Tag> tags = new ArrayList<>();
	            int tagId = (int) row.getCell(4).getNumericCellValue();
	            String tagName = row.getCell(5).getStringCellValue();
	            Tag tag = new Tag(tagId, tagName);
	            tags.add(tag);  
	            
	            String status = row.getCell(6).getStringCellValue();
	            
	            Category category = new Category(categoryId, categoryName);
	            
	            Object[] petData = new Object[] {
	                0,  
	                category,
	                petName,
	                photoUrls,
	                tags,  
	                status
	            };
	            
	            petDataList.add(petData);
	        }
	    }

	    Object[][] petDataArray = new Object[petDataList.size()][];
	    petDataList.toArray(petDataArray);
	    return petDataArray;
	}
	
	public Object[][] getOrderData(String sheetName) throws IOException {
	    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + path);
	    workbook = new XSSFWorkbook(fileInputStream);
	    sheet = workbook.getSheet(sheetName);
	    
	    int rowCount = sheet.getPhysicalNumberOfRows();
	    List<Object[]> orderDataList = new ArrayList<>();
	    
	    for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
	        Row row = sheet.getRow(rowIndex);
	        
	        if (row != null) {
	            int OrderID = (int) row.getCell(0).getNumericCellValue();
	            int PetID = (int) row.getCell(1).getNumericCellValue();
	            int Quanitity =(int) row.getCell(2).getNumericCellValue();
	            String ShipDate = row.getCell(3).getStringCellValue();
	            String status = row.getCell(4).getStringCellValue();
	            boolean complete = row.getCell(5).getBooleanCellValue();
	
	            Object[] orderData = new Object[] {
	            		OrderID,
	            		PetID,
	            		Quanitity,
	            		ShipDate,  
	                    status,
	                    complete
	            };
	            
	            orderDataList.add(orderData);
	        }
	    }

	    Object[][] orderDataArray = new Object[orderDataList.size()][];
	    orderDataList.toArray(orderDataArray);
	    return orderDataArray;
	}

}
