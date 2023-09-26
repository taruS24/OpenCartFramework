package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	public static Workbook workBook;
	public static Sheet sheet;

	public static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/TestData.xlsx";

	public static Object[][] getSheetData(String sheetName) {

		Object[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			workBook = WorkbookFactory.create(fis);
			sheet = workBook.getSheet(sheetName);
			System.out.println("Last Sheet Number is: " + sheet.getLastRowNum());//counts from 0 (if sheet has 1 to 5,
																					// it will count last row as 4)
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return data;

	}

}
