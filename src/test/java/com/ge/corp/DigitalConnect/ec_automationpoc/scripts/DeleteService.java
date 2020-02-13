package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class DeleteService {

	ExcelHandler xlHandler = new ExcelHandler(
			System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx", "ECSetup_TestData");

	@Test(priority = 0)
	public void cfLogin() {
		try {

			String loginResult = JsonTesting.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute")
					+ "&&" + xlHandler.getData("CF_Login", "CommandToExecute"));
			if (loginResult.contains(xlHandler.getData("CF_Login", "ExpectedResult"))) {
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

	@Test(priority = 1)
	public void stopGateway() {
		try {
			String stopGatewayResult = JsonTesting
					.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") + " && " + "cf stop "
							+ xlHandler.getData("GatewayName", "Value"));
			if (stopGatewayResult.contains(xlHandler.getData("GatewayName", "ExpectedResult"))
					|| stopGatewayResult.contains("App " + xlHandler.getData("GatewayName", "Value") + " "
							+ xlHandler.getData("GatewayName", "ExpectedResult1"))
					|| stopGatewayResult.contains("App " + xlHandler.getData("GatewayName", "Value") + " "
							+ xlHandler.getData("GatewayName", "ExpectedResult3"))) {
				System.out.println(
						xlHandler.getData("GatewayName", "Value") + " stopped successfully or is already stopped.");
			} else {
				System.out.println("Fail to stop gateway");
			}
			assertTrue(stopGatewayResult.contains(xlHandler.getData("GatewayName", "ExpectedResult"))
					|| stopGatewayResult.contains("App " + xlHandler.getData("GatewayName", "Value") + " "
							+ xlHandler.getData("GatewayName", "ExpectedResult1"))
					|| stopGatewayResult.contains("App " + xlHandler.getData("GatewayName", "Value") + " "
							+ xlHandler.getData("GatewayName", "ExpectedResult3")),
					"Fail to stop gateway");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void deleteGateway() {
		try {
			String deleteGatewayResult = JsonTesting
					.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") + " && "
							+ "echo Y | cf delete " + xlHandler.getData("GatewayName", "Value"));
			if (deleteGatewayResult.contains(xlHandler.getData("GatewayName", "ExpectedResult"))
					|| deleteGatewayResult.contains(xlHandler.getData("GatewayName", "ExpectedResult2"))) {
				System.out.println(
						xlHandler.getData("GatewayName", "Value") + " deleted successfully or does not exist.");
			} else {
				System.out.println("Fail to delete gateway");
			}
			assertTrue(
					deleteGatewayResult.contains("OK")
							|| deleteGatewayResult.contains(xlHandler.getData("GatewayName", "ExpectedResult2")),
					"Fail to delete gateway");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void deleteServiceKey() {
		try {

			/*
			 * String availableServicesResult = JsonTesting
			 * .runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") +
			 * " && cf services " + xlHandler.getData("EC_Service_Name", "Value"));
			 * assertTrue(availableServicesResult.contains(xlHandler.getData(
			 * "EC_Service_Name", "Value")), "Service not found");
			 * 
			 * String availableServiceKeysResult = JsonTesting
			 * .runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") +
			 * " && cf service-keys " + xlHandler.getData("EC_Service_Name", "Value"));
			 * 
			 * assertTrue(availableServiceKeysResult.contains(xlHandler.getData(
			 * "EC_ServiceKey", "Value")), "Service Key is not available");
			 */

			String deleteServiceKeyResult = JsonTesting
					.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") + " && "
							+ "echo Y | cf delete-service-key " + xlHandler.getData("EC_Service_Name", "Value") + " "
							+ xlHandler.getData("EC_ServiceKey", "Value"));

			if (deleteServiceKeyResult.contains(xlHandler.getData("EC_ServiceKey", "ExpectedResult"))
					|| deleteServiceKeyResult.contains(xlHandler.getData("EC_ServiceKey", "ExpectedResult1"))) {
				System.out.println(xlHandler.getData("EC_ServiceKey", "Value") + " deleted successfully or Service Key "
						+ xlHandler.getData("EC_ServiceKey", "Value") + " does not exist for service instance "
						+ xlHandler.getData("EC_Service_Name", "Value") + ".");
			} else {
				System.out.println("Fail to delete service key");
			}
			assertTrue(
					deleteServiceKeyResult.contains(xlHandler.getData("EC_ServiceKey", "ExpectedResult"))
							|| deleteServiceKeyResult.contains(xlHandler.getData("EC_ServiceKey", "ExpectedResult1")),
					"Fail to delete service key");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void deleteService() {
		try {
			String deleteServiceResult = JsonTesting
					.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") + " && "
							+ "echo Y | cf delete-service " + xlHandler.getData("EC_Service_Name", "Value"));

			if (deleteServiceResult.contains(xlHandler.getData("EC_Service_Name", "ExpectedResult"))
					|| deleteServiceResult.contains(xlHandler.getData("EC_Service_Name", "ExpectedResult1"))) {
				System.out.println(xlHandler.getData("EC_Service_Name", "Value") + " deleted successfully or Service "
						+ xlHandler.getData("EC_Service_Name", "Value") + " does not exist.");
			} else {
				System.out.println("Fail to delete service");
			}
			assertTrue(
					deleteServiceResult.contains(xlHandler.getData("EC_Service_Name", "ExpectedResult"))
							|| deleteServiceResult.contains(xlHandler.getData("EC_Service_Name", "ExpectedResult1")),
					"Fail to delete service");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
