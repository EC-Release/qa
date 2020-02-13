package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class CreateUAAService {
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;

	public void create_UAA_Service() throws IOException {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		try {
			jsonTesting = new JsonTesting();
			System.out.println("Create UAA Service ----- " + xlHandler.getData("SetProxy", "CommandToExecute")
					+ "&& cf create-service predix-uaa Free " + xlHandler.getData("UAA_Service_Name", "Value") + " -c "
					+ xlHandler.getData("UAASecretFileName", "Value"));
			JsonTesting.navigateToDirectory(
					xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value"));
			String clientSecret = JsonTesting.getKeyValueFromUaa_Secret(
					xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value") + "\\\\"
							+ xlHandler.getData("UAASecretFileName", "Value"),
					"adminClientSecret");
			System.out.println("UAA Secret value ---- " + clientSecret);
			String createUAAServiceResult = JsonTesting
					.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute")
							+ " && cf create-service predix-uaa Free " + xlHandler.getData("UAA_Service_Name", "Value")
							+ " -c " + xlHandler.getData("UAASecretFileName", "Value"));
			if (createUAAServiceResult.contains("OK")) {
				System.out.println(createUAAServiceResult);
				System.out.println("Uaa service created successfully");
			} else {
				System.out.println("Failed to create uaa service");
			}
			assertTrue(createUAAServiceResult.contains("OK"), "Failed to create uaa service");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
