package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class EC_Setup_With_ExistingService extends TestBase {

	ExcelHandler xlHandler = new ExcelHandler(
			System.getProperty("user.dir") + "/src/test/resources/TestData/ECTestData.xlsx",
			"ECSetupUsingExistingService");

	String dashboardURL;
	String oauthScope;
	String gatewayURL;

	String clientID = xlHandler.getData("ClientID", "Value");
	String clientSecret = xlHandler.getData("ClientSecret", "Value");
	String clientLPT = xlHandler.getData("Client_lpt", "Value");

	Map map;

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
	public void ecSetupUsingExistingService() {
		try {
			/*
			 * String existingServicesResult = JsonTesting
			 * .runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute") +
			 * "&& cf services");
			 * assertTrue(existingServicesResult.contains(xlHandler.getData(
			 * "EC_Service_Name", "Value")), "Service instance '" +
			 * xlHandler.getData("EC_Service_Name", "Value") + "' not found");
			 */

			// Create Service Key
			String createServiceKeyResult = JsonTesting
					.runCloudFoundryCommand(xlHandler.getData("SetProxy", "CommandToExecute")
							+ "&& cf csk ecservice-testexisting " + xlHandler.getData("EC_ServiceKey", "Value"));
			assertTrue(createServiceKeyResult.contains(xlHandler.getData("EC_ServiceKey", "ExpectedResult")),
					"Fail to create service key");

			// Retrieve Service Key Info
			String retrieveServiceKeyInfoResult = JsonTesting.runCloudFoundryCommand(
					xlHandler.getData("SetProxy", "CommandToExecute") + "&& cf service-key ecservice-testexisting "
							+ xlHandler.getData("EC_ServiceKey", "Value"));

			// Get the required info
			String requiredECServiceKeyInfo = retrieveServiceKeyInfoResult
					.substring(retrieveServiceKeyInfoResult.indexOf('{'));

			// Need to get clarification on below
			// Add the service key info to VCAP file
			JsonTesting.createVCAP(System.getProperty("user.dir") + "/TestExistingService/VCAP.json",
					requiredECServiceKeyInfo);

			// Get the VCAP values
			map = JsonTesting
					.getKeyValueFromVCAPFile(System.getProperty("user.dir") + "/TestExistingService/VCAP.json");
			oauthScope = (String) map.get("oauth-scope");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void loginUaaDashboard() throws IOException, JSONException, InterruptedException {
		LoginPageTest loginTest = new LoginPageTest();
		try {
			dashboardURL = JsonTesting.getKeyValueFromUaa_Secret(
					System.getProperty("user.dir") + "/TestExistingService/uaa-secret.json", "dashboardUrl");
			System.out.println("Dashboard URL For Existing Service ----- " + dashboardURL);
			String secret = JsonTesting.getKeyValueFromUaa_Secret(
					System.getProperty("user.dir") + "/TestExistingService/uaa-secret.json", "adminClientSecret");
			System.out.println("Password To Login Dashboard ----- " + secret);
			loginTest.setup(dashboardURL);
			loginTest.loginTestExisting(secret);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void createUaaClient() {
		UaaDashboardPageTest_CreateClient createClientTest = new UaaDashboardPageTest_CreateClient();
		try {
			System.out.println("Authentication Scope : " + oauthScope);
			createClientTest.createClient1(clientID, clientSecret, oauthScope);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 4)
	public void ecSetupContinue() throws IOException, ParseException, JSONException, InterruptedException {
		try {
			map = JsonTesting
					.getKeyValueFromVCAPFile(System.getProperty("user.dir") + "/TestExistingService/VCAP.json");
			JsonTesting.editGatewayFile(System.getProperty("user.dir") + "/TestExistingService/Gateway/gateway.sh",
					map);
			System.out.println("*****gateway.sh file updated successfully*****");

			// Push the gateway
			String pushGatewayResult = JsonTesting.runCloudFoundryCommand("cd " + System.getProperty("user.dir")
					+ "/TestExistingService/Gateway" + " && " + xlHandler.getData("SetProxy", "CommandToExecute")
					+ " && cf push " + xlHandler.getData("GatewayName", "Value"));

			gatewayURL = JsonTesting.fetchGatewayURL(pushGatewayResult);

			System.out.println("Gateway URL ------" + gatewayURL);
			System.out.println("HST in server.sh file ------ wss://" + gatewayURL + "/agent");
			System.out.println("Gateway Health Check URL ------ https://" + gatewayURL + "/health");

			// Run the server.bat file
			JsonTesting.editServerBatchFile(System.getProperty("user.dir") + "/TestExistingService/Server/server.bat",
					map, gatewayURL, clientID, clientSecret);
			System.out.println("*****server.bat file updated successfully*****");
			JsonTesting.run_batFileenhancement("cmd /c server.bat",
					System.getProperty("user.dir") + "/TestExistingService/Server",
					"super connection established for gateway inst#");

			// Run the client.bat file
			JsonTesting.editClientBatchFile(System.getProperty("user.dir") + "/TestExistingService/Client/client.bat",
					map, gatewayURL, clientID, clientSecret, clientLPT);
			System.out.println("*****client.bat file updated successfully*****");
			JsonTesting.run_batFileenhancement("cmd /c client.bat",
					System.getProperty("user.dir") + "/TestExistingService/Client", "client is listening on port");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(priority = 5)
	public void healthConnection() {
		//UaaDashboardPageTest_CreateClient createClientTest = new UaaDashboardPageTest_CreateClient();
		try {
			driver.navigate().to("https://" + gatewayURL + "/health");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
