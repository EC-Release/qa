package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.IOException;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class RetrieveUAAServiceKeyInfo {
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;

	public String retrieve_UAA_Service_Info() throws IOException {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		String uaaServiceInfo = null;
		try {
			jsonTesting = new JsonTesting();
			System.out.println("Retrieve UAA Service Key Info ----- " + "cf service-key "
					+ xlHandler.getData("UAA_Service_Name", "Value") + " "
					+ xlHandler.getData("UAA_ServiceKey", "Value"));

			uaaServiceInfo = JsonTesting
					.runCloudFoundryCommand("cf service-key " + xlHandler.getData("UAA_Service_Name", "Value") + " "
							+ xlHandler.getData("UAA_ServiceKey", "Value"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uaaServiceInfo;
	}

}
