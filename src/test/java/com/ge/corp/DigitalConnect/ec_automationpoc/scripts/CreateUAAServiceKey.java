package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.IOException;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class CreateUAAServiceKey {
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;

	public void create_ServiceKey_For_UAA_Service() throws IOException {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		
		try {
			jsonTesting = new JsonTesting();
			System.out.println("Create UAA Service Key----- " + "cf csk " + xlHandler.getData("UAA_Service_Name", "Value")
					+ " " + xlHandler.getData("UAA_ServiceKey", "Value"));

			String createUAAServiceKeyResult = JsonTesting
					.runCloudFoundryCommand("cf csk " + xlHandler.getData("UAA_Service_Name", "Value") + " "
							+ xlHandler.getData("UAA_ServiceKey", "Value"));
			if (createUAAServiceKeyResult.contains("OK")) {
				System.out.println(createUAAServiceKeyResult);
				System.out.println("Uaa service key created successfully");
			} else {
				System.out.println("Failed to created uaa service key");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
