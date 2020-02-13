package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;



public class EC_Setup_New extends TestBase {

	JsonTesting jsonTesting;
	String dashboardURL;
	String oauthScope;
	String gatewayURL;

	String clientID = "uaaClient1";
	String clientSecret = "uaaClient1Secret";
	String confirmClientSecret = "uaaClient1Secret";
	String lpt = "7990";
	Map map;

	@Test(priority = 1)
	public void ecSetup() {
		try {
			jsonTesting = new JsonTesting();
			JsonTesting.createJsonCredential(uaa_secret_path, "password");
			String[] command = new String[3];
			command[0] = "cmd";
			command[1] = "/c";
			command[2] = "cd c:\\ && cd users && cd sareddyc && cd SampleEC && set https_proxy=http://3.28.29.241:88/ && cf login -a https://api.system.aws-usw02-dev.ice.predix.io -u prasad_alokam@hotmail.com -p eC7eamR0ck! -o enterprise-connect -s Dev && cf create-service predix-uaa Free sampleec-uaaservice41 -c uaa-secret.json && cf csk  sampleec-uaaservice41 sampleec-uaaservice41-skey && cf service-key  sampleec-uaaservice41 sampleec-uaaservice41-skey";

			Process p = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			// String line = reader.readLine();
			String line;

			StringBuilder strBuilder = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				// line = reader.readLine();
				strBuilder.append(line).append("\n");
			}

			String cmdResult = strBuilder.toString();
			System.out.println("********************************************");
			System.out.println(cmdResult);
			System.out.println("--------------------------------------------");
			String requiredResult = cmdResult.substring(cmdResult.indexOf('{'));
			JsonTesting.appendServiceKeyOutputToJsonCredential(uaa_secret_path, requiredResult);
			JsonTesting.createJsonFileTrustedIssuerID(uaa_trustedIssuerId_path,
					JsonTesting.getIssuerId(requiredResult));

			dashboardURL = JsonTesting.getKeyValueFromUaa_Secret(uaa_secret_path, "dashboardUrl");

			String[] createECService = new String[3];
			createECService[0] = "cmd";
			createECService[1] = "/c";
			createECService[2] = "cd c:\\ && cd users && cd sareddyc && cd SampleEC && set https_proxy=http://3.28.29.241:88/ && cf create-service enterprise-connect Oneway-TLS sampleec-ecservice41 -c uaa-issuerid.json";
			Process p1 = Runtime.getRuntime().exec(createECService);
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			String line1 = reader1.readLine();

			StringBuilder strBuilder1 = new StringBuilder();
			while (line1 != null) {
				// System.out.println(line);
				line1 = reader1.readLine();
				strBuilder1.append(line1).append("\n");
			}

			String cmdResult1 = strBuilder1.toString();
			JsonTesting.sleep();

			String[] status_ECService = new String[3];
			status_ECService[0] = "cmd";
			status_ECService[1] = "/c";
			status_ECService[2] = "cd c:\\ && cd users && cd sareddyc && cd SampleEC && set https_proxy=http://3.28.29.241:88/ && cf service sampleec-ecservice41";
			Process p3 = Runtime.getRuntime().exec(status_ECService);
			BufferedReader reader3 = new BufferedReader(new InputStreamReader(p3.getInputStream()));
			String line3 = reader3.readLine();

			StringBuilder strBuilder3 = new StringBuilder();
			while (line3 != null) {
				// System.out.println(line);
				line3 = reader3.readLine();
				strBuilder3.append(line3).append("\n");
			}

			String cmdResult3 = strBuilder3.toString();
			System.out.println("************************************************");
			System.out.println(cmdResult3);
			System.out.println("------------------------------------------------");

			String[] createECServiceKey_GetInfo = new String[3];
			createECServiceKey_GetInfo[0] = "cmd";
			createECServiceKey_GetInfo[1] = "/c";
			createECServiceKey_GetInfo[2] = "cd c:\\ && cd users && cd sareddyc && cd SampleEC && set https_proxy=http://3.28.29.241:88/ && cf csk sampleec-ecservice41 sampleec-ecservice41-skey && cf service-key sampleec-ecservice41 sampleec-ecservice41-skey";
			Process p2 = Runtime.getRuntime().exec(createECServiceKey_GetInfo);
			BufferedReader reader2 = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			String line2 = reader2.readLine();

			StringBuilder strBuilder2 = new StringBuilder();
			while (line2 != null) {
				// System.out.println(line);
				line2 = reader2.readLine();
				strBuilder2.append(line2).append("\n");
			}

			String cmdResult2 = strBuilder2.toString();
			System.out.println("************************************************");
			System.out.println(cmdResult2);
			System.out.println("------------------------------------------------");
			String requiredECServiceKeyInfo = cmdResult2.substring(cmdResult2.indexOf('{'));
			JsonTesting.createVCAP(VCAP_path, requiredECServiceKeyInfo);

			map = JsonTesting.getKeyValueFromVCAPFile(VCAP_path);
			oauthScope = (String) map.get("oauth-scope");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String Error;
			while ((Error = stdError.readLine()) != null) {
				System.out.println(Error);
			}
			while ((Error = stdInput.readLine()) != null) {
				System.out.println(Error);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void loginUaaDashboard() throws IOException, JSONException, InterruptedException {
		LoginPageTest loginTest = new LoginPageTest();
		System.out.println("Dashboard URL : " + dashboardURL);
		loginTest.setup(dashboardURL);
		loginTest.loginTest();
	}

	@Test(priority = 3)
	public void createUaaClient() {
		UaaDashboardPageTest_CreateClient createClientTest = new UaaDashboardPageTest_CreateClient();
		System.out.println("Authentication Scope : " + oauthScope);
		createClientTest.createClient(clientID, clientSecret, confirmClientSecret, oauthScope);
		System.out.println("*****End of Create UAA Client*****");
	}

	@Test(priority = 4)
	public void ecSetupContinue() throws IOException, ParseException, JSONException, InterruptedException {
		JsonTesting.copyFile("C:\\Users\\sareddyc\\Automation\\gateway.sh", directoryPath + "Gateway");
		map = JsonTesting.getKeyValueFromVCAPFile(VCAP_path);
		JsonTesting.editGatewayFile(directoryPath + "Gateway\\gateway.sh", map);
		System.out.println("*****gateway.sh file updated successfully*****");

		JsonTesting.copyFile("C:\\Users\\sareddyc\\Automation\\manifest.yml", directoryPath + "Gateway");
		System.out.println("*****manifest.yml file copied successfully*****");

		// Push the gateway
		String[] pushGateway = new String[3];
		pushGateway[0] = "cmd";
		pushGateway[1] = "/c";
		pushGateway[2] = "cd c:\\ && cd users && cd sareddyc && cd SampleEC && cd Gateway && set https_proxy=http://3.28.29.241:88/ && cf push sampleec-ecservice41-gateway";
		Process p4 = Runtime.getRuntime().exec(pushGateway);
		BufferedReader reader4 = new BufferedReader(new InputStreamReader(p4.getInputStream()));
		String line4 = reader4.readLine();

		StringBuilder strBuilder4 = new StringBuilder();
		while (line4 != null) {
			// System.out.println(line);
			line4 = reader4.readLine();
			strBuilder4.append(line4).append("\n");
		}

		String cmdResult4 = strBuilder4.toString();
		System.out.println(cmdResult4);
		gatewayURL = JsonTesting.fetchGatewayURL(cmdResult4);
		System.out.println("Gateway URL ------" + gatewayURL);
		System.out.println("HST in server.sh file ------ wss://" + gatewayURL + "/agent");
		System.out.println("Gateway Health Check URL ------ https://" + gatewayURL + "/health");

		JsonTesting.copyFile("C:\\Users\\sareddyc\\Automation\\server.bat", directoryPath + "Server");
		System.out.println("editing server.bat file");
		JsonTesting.editServerBatchFile(directoryPath + "Server\\server.bat", map, gatewayURL, clientID, clientSecret);
		System.out.println("*****server.bat file updated successfully*****");
		JsonTesting.run_batFileenhancement("cmd /c server.bat", directoryPath + "Server",
				"super connection established for gateway inst#");

		// Run the server.sh file

		JsonTesting.copyFile("C:\\Users\\sareddyc\\Automation\\client.bat", directoryPath + "Client");
		System.out.println("editing client.bat file");
		JsonTesting.editClientBatchFile(directoryPath + "Client\\client.bat", map, gatewayURL, clientID, clientSecret, lpt);
		System.out.println("*****client.bat file updated successfully*****");
		// JsonTesting.run_batFile(directoryPath + "Client");
		JsonTesting.run_batFileenhancement("cmd /c client.bat", directoryPath + "Client",
				"client is listening on port");
	}

	@Test(priority = 5)
	public void healthConnection() {
		UaaDashboardPageTest_CreateClient createClientTest = new UaaDashboardPageTest_CreateClient();
		System.out.println("Authentication Scope : " + oauthScope);
		createClientTest.createClient(clientID, clientSecret, confirmClientSecret, oauthScope);
		driver.navigate().to("https://" + gatewayURL + "/health");
	}

}
