package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import static org.testng.Assert.assertTrue;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class CloudFoundryLogin {
	
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;
	public void cfLogin() {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");

		try {
			jsonTesting = new JsonTesting();
			JsonTesting.navigateToDirectory(
					xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value"));
			System.out.println("Cloud Foundry Login ----- " + xlHandler.getData("SetProxy", "CommandToExecute") + "&&"
					+ xlHandler.getData("CF_Login", "CommandToExecute"));
			String loginResult = JsonTesting.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute")
					+ "&&" + xlHandler.getData("CF_Login", "CommandToExecute"));
			if (loginResult.contains(xlHandler.getData("CF_Login", "ExpectedResult"))) {
				System.out.println(loginResult);
				System.out.println("login Success");
			} else {
				System.out.println("Login Fail");
			}
			assertTrue(loginResult.contains(xlHandler.getData("CF_Login", "ExpectedResult")),
					"Cloud Foundry login failed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
