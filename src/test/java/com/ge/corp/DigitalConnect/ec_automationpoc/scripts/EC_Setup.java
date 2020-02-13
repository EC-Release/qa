package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import org.testng.annotations.Test;
import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class EC_Setup extends BaseTest_EnterpriseConnect {

	String dashBoardURL;
	String password;
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;

	@Test(priority = 1)
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

	@Test(priority = 2)
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
				// System.out.println(loginResult);
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

	@Test(priority = 3)
	public void create_UAA_Service() throws IOException {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		try {
			jsonTesting = new JsonTesting();

			System.out.println("Create UAA Service Command ----- " + xlHandler.getData("SetProxy", "CommandToExecute")
					+ " && cf create-service predix-uaa Free " + xlHandler.getData("UAA_Service_Name", "Value") + " -c "
					+ xlHandler.getData("UAASecretFileName", "Value"));

			JsonTesting.navigateToDirectory(
					xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value"));

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

	public void create_ServiceKey_For_UAA_Service() throws IOException {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		try {
			jsonTesting = new JsonTesting();
			System.out.println("Create UAA Service ----- " + xlHandler.getData("SetProxy", "CommandToExecute")
					+ "&& cf create-service predix-uaa Free " + xlHandler.getData("UAA_Service_Name", "Value") + " -c "
					+ xlHandler.getData("UAASecretFileName", "Value"));

			String createUAAServiceKeyResult = JsonTesting.runCloudFoundryCommand(
					"cf create-service predix-uaa Free " + xlHandler.getData("UAA_Service_Name", "Value") + " -c "
							+ xlHandler.getData("UAASecretFileName", "Value"));
			if (createUAAServiceKeyResult.contains("OK")) {
				System.out.println(createUAAServiceKeyResult);
				System.out.println("Uaa service created successfully");
			} else {
				System.out.println("Failed to created uaa service");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String retrieve_UAA_Service_Info() throws IOException {
		xlHandler = new ExcelHandler(System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
				"ECSetup_TestData");
		String uaaServiceInfo = null;
		try {
			jsonTesting = new JsonTesting();
			uaaServiceInfo = JsonTesting.runCloudFoundryCommand(
					"cf create-service predix-uaa Free " + xlHandler.getData("UAA_Service_Name", "Value") + " -c "
							+ xlHandler.getData("UAASecretFileName", "Value"));
			if (uaaServiceInfo.contains("OK")) {
				System.out.println(uaaServiceInfo);
				System.out.println("Uaa service created successfully");
			} else {
				System.out.println("Failed to created uaa service");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return uaaServiceInfo;
	}
}
