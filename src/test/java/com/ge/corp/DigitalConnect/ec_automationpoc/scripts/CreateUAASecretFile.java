package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class CreateUAASecretFile {
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;
	public void createUAA_Secret_JSONFile() {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		try {
			jsonTesting = new JsonTesting();
			JsonTesting.createJsonCredential(
					xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value") + "\\\\"
							+ xlHandler.getData("UAASecretFileName", "Value"),
					xlHandler.getData("AdminClientSecret", "Value"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
