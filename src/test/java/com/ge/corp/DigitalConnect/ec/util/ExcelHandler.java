package com.ge.corp.DigitalConnect.ec.util;

import java.util.Map;
import java.util.TreeMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelHandler {
	public static String datafilePath;
	public static String sheetName;

	public ExcelHandler(String datafilePath, String sheetName) {
		this.datafilePath = datafilePath;
		this.sheetName = sheetName;
	}

	public String getData(String testCaseName, String desiredColumnName) {
		Map<String, String> testDataInMap = new TreeMap<String, String>();
		String desiredColumnValue;
		String query = null;
		//query = String.format("Select * from %s where TestCaseName='%s'", sheetName, testCaseName);
		query = String.format("Select * from %s where TestCaseName='%s'", sheetName, testCaseName);
		Fillo fillo = new Fillo();
		Connection connection = null;
		Recordset recordSet = null;
		try {
			connection = fillo.getConnection(datafilePath);
			recordSet = connection.executeQuery(query);
			while (recordSet.next()) {
				for (String field : recordSet.getFieldNames()) {					
					testDataInMap.put(field, recordSet.getField(field));
				}
			}
		} catch (FilloException e) {
			e.printStackTrace();
			try {
				throw new Exception("Test Data not found..");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		recordSet.close();
		connection.close();

		desiredColumnValue = testDataInMap.get(desiredColumnName);
		return desiredColumnValue;
	}
	
	public static void main(String[] args) {
		ExcelHandler xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx", "ECSetup_TestData");
		System.out.println("Cloud Foundry Login Command --- " + xlHandler.getData("CF_Login", "CommandToExecute"));
		System.out.println("Proxy Settings For Cloud Foundry --- " + xlHandler.getData("SetProxy", "CommandToExecute"));
	}

}
