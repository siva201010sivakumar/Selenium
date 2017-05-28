package com.bankbazaar.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelUtility {

	static FileInputStream fis = null;
	static Workbook wb = null;
	static Sheet s1 = null;

	public static final int CELL_RUNSTATUS_COL_NUM = 2;
	public static final int CELL_TESTNAME_COL_NUM = 1;
	public static final int CELL_RESULT_COL_NUM = 16;
	public static final int CELL_ERRORMSG_COL_NUM = 17;
	public static final int CELL_TESTDATA_START_COL_NUM = 3;

	static {
		try {
			fis = new FileInputStream(".\\Config\\BankBazaarTestData.xls");
			wb = WorkbookFactory.create(fis);
			s1 = wb.getSheet("Sheet1");
		} catch (Exception e) {

		}
	}

	public static int getTestCaseName_status_y_rowcount(String testName) {
		int count = 0;

		for (int eachRow = 0; eachRow < s1.getPhysicalNumberOfRows(); eachRow++) {

			Row r = s1.getRow(eachRow);

			Cell c1 = r.getCell(ExcelUtility.CELL_TESTNAME_COL_NUM);
			Cell c2 = r.getCell(ExcelUtility.CELL_RUNSTATUS_COL_NUM);

			String testName_cellData = c1.getStringCellValue();
			String runStatus_cellData = c2.getStringCellValue();

			if (runStatus_cellData.equalsIgnoreCase("Y") && testName_cellData.equalsIgnoreCase(testName)) {
				count++;
			}
		}
		return count;
	}

	public static int getTestCaseName_status_y_cellDataCount(String testName) {

		for (int eachRow = 0; eachRow < s1.getPhysicalNumberOfRows(); eachRow++) {

			Row r = s1.getRow(eachRow);

			Cell c1 = r.getCell(ExcelUtility.CELL_TESTNAME_COL_NUM);
			Cell c2 = r.getCell(ExcelUtility.CELL_RUNSTATUS_COL_NUM);

			String testName_cellData = c1.getStringCellValue();
			String runStatus_cellData = c2.getStringCellValue();

			if (runStatus_cellData.equalsIgnoreCase("Y") && testName_cellData.equalsIgnoreCase(testName)) {

				return r.getLastCellNum() - ExcelUtility.CELL_TESTDATA_START_COL_NUM;

			}
		}
		return 0;

	}

	@DataProvider(name="CreditCardSearchData")
	public static String[][] getTestCaseName_status_y_storeCellData(Method m) {
		
		String testName = m.getName();
		
		String[][] testData = new String [ExcelUtility.getTestCaseName_status_y_rowcount(testName)][ExcelUtility.getTestCaseName_status_y_cellDataCount(testName)+1];
		
		int rowIndex=0;
		for (int eachRow = 0; eachRow < s1.getPhysicalNumberOfRows(); eachRow++) {
			Row r = s1.getRow(eachRow);
			
			int cellIndex=0;
			Cell c1 = r.getCell(ExcelUtility.CELL_TESTNAME_COL_NUM);
			Cell c2 = r.getCell(ExcelUtility.CELL_RUNSTATUS_COL_NUM);
			
			String testName_cellData = c1.getStringCellValue();
			String runStatus_cellData = c2.getStringCellValue();

			if (runStatus_cellData.equalsIgnoreCase("Y") && testName_cellData.equalsIgnoreCase(testName)) {

				int cellCount = r.getLastCellNum();

				for (int cellNum = ExcelUtility.CELL_TESTDATA_START_COL_NUM; cellNum < cellCount; cellNum++) {
					Cell cell = r.getCell(cellNum);
					String data = cell.getStringCellValue();
					//4.
					testData[rowIndex][cellIndex++]=data;
				}
				
				testData[rowIndex][cellIndex] = "" + eachRow;
				rowIndex++;
			}
		}
		return testData;
	}
	
	public static void writeToSheetEx1(int rowNum, int cellNum, String result) throws IOException{
		
		Row r = s1.getRow(rowNum);
		Cell c = r.createCell(cellNum);
		
		c.setCellValue(result);
		
		FileOutputStream fos = new FileOutputStream("D:\\SeleniumTrainingByJitendra\\Data\\duplicate\\BankBazaarTestData.xls");
		wb.write(fos);
		
		fos.close();
	}
}
