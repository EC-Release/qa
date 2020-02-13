package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ddf.EscherColorRef.SysIndexSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JsonTesting {

	public static void createJsonCredential(String path, String password) throws JSONException {
		File file = new File(path);
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
			JSONObject objcred = new JSONObject();
			objcred.put("adminClientSecret", password);
			fileWriter.write(objcred.toString());
			fileWriter.close();

		} catch (IOException e) {
			System.out.println("Exception occured..");
			e.printStackTrace();
		}

		System.out.println("JSON Object Successfully written to the file!! ----->" + file.getName());

	}
	
	public static void navigateToDirectory(String pathOfDirectory) throws IOException {
		String[] command = new String[3];
		command[0] = "cmd";
		command[1] = "/c";
		command[2] = "cd " + pathOfDirectory;
		Process p = Runtime.getRuntime().exec(command);
		System.out.println("Successfully navigated to " + pathOfDirectory);
	}
	
	public static String runCloudFoundryCommand(String commandToExecute) throws IOException {		
		String[] command = new String[3];
		command[0] = "cmd";
		command[1] = "/c";
		command[2] = commandToExecute;

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
		
		return cmdResult;
	}

	public static void appendServiceKeyOutputToJsonCredential(String path, String serviceKeyOutput)
			throws IOException, JSONException {

		File file = new File(path);
		FileWriter fileWriter = new FileWriter(file, true);
		JSONObject jsonObject = new JSONObject(serviceKeyOutput);
		fileWriter.write(jsonObject.toString());
		fileWriter.close();
		System.out.println(
				"JSON Object- Service KeyOutput-   Successfully appended to the file!! ------->" + file.getName());
	}

	public static void createVCAP(String path, String serviceKeyInfo) throws IOException, JSONException {

		File file = new File(path);
		FileWriter fileWriter = new FileWriter(file);
		JSONObject jsonObject = new JSONObject(serviceKeyInfo);
		fileWriter.write(jsonObject.toString());
		fileWriter.close();
		System.out.println(
				"JSON Object- Service Key Info - Successfully written to the file!! ------->" + file.getName());
	}

	public static String getIssuerId(String serviceKeyOutput) throws IOException, JSONException {
		JSONObject jsonObject = new JSONObject(serviceKeyOutput);
		String isuerId = (String) jsonObject.get("issuerId");
		return isuerId;
	}

	public static void createJsonFileTrustedIssuerID(String path, String issuerID) throws IOException, JSONException {
		File file = new File(path);
		FileWriter fileWriter = new FileWriter(file);
		JSONArray jaTrusterIssuerIDs = new JSONArray();
		jaTrusterIssuerIDs.put(issuerID);
		JSONObject josnObject = new JSONObject();
		josnObject.put("trustedIssuerIds", jaTrusterIssuerIDs);
		fileWriter.write(josnObject.toString());
		fileWriter.close();
		System.out.println("JSON Object Successfully written to the file!! ----->" + file.getName());
	}

	public static int ordinalIndexOf(String str, String substr, int n) {
		int pos = -1;
		do {
			pos = str.indexOf(substr, pos + 1);
		} while (n-- > 0 && pos != -1);
		return pos;
	}

	public static String getKeyValueFromUaa_Secret(String path, String key) throws IOException, JSONException {
		File file = new File(path);
		String value;
		FileReader fileReader = new FileReader(file);
		BufferedReader br = new BufferedReader(fileReader);
		JSONObject obj = null;

		String lineStr = br.readLine();
		lineStr = "[" + lineStr + "]";
		StringBuilder sb = new StringBuilder(lineStr);
		sb.insert(33, ",");
		JSONArray jsonArray = new JSONArray(sb.toString());
		if (key.equals("adminClientSecret")) {

			obj = (JSONObject) jsonArray.get(0);
			value = (String) obj.get("adminClientSecret");

		} else {

			obj = (JSONObject) jsonArray.get(1);
			value = (String) obj.get(key);

		}
		return value;

		/*
		 * if(key.equals("adminClientSecret")) { return IntStream.range(0,
		 * jsonArray.length()) .mapToObj(index ->
		 * ((JSONObject)jsonArray.get(index)).optString(key))
		 * .collect(Collectors.toList()).get(0); } else { return IntStream.range(0,
		 * jsonArray.length()) .mapToObj(index ->
		 * ((JSONObject)jsonArray.get(index)).optString(key))
		 * .collect(Collectors.toList()).get(1); }
		 */

	}

	public static Map getKeyValueFromVCAPFile(String path)
			throws FileNotFoundException, IOException, ParseException, JSONException {

		Map attrubuteMap = new HashMap();
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new FileReader(path));
		org.json.simple.JSONObject jsonObject = null;
		jsonObject = (org.json.simple.JSONObject) obj;
		Object ecInfo = jsonObject.get("ec-info");

		Map<String, List<String>> ec_ecinfo = ((Map) jsonObject.get("ec-info"));
		for (Map.Entry<String, List<String>> pair : ec_ecinfo.entrySet()) {
			attrubuteMap.put(pair.getKey(), pair.getValue());
		}

		Map<String, List<String>> zone = ((Map) jsonObject.get("zone"));
		for (Map.Entry<String, List<String>> pair : zone.entrySet()) {
			attrubuteMap.put(pair.getKey(), pair.getValue());
		}

		String service_uri = (String) jsonObject.get("service-uri");
		String usage_doc = (String) jsonObject.get("usage-doc");

		attrubuteMap.put("service-uri", service_uri);
		attrubuteMap.put("usage-doc", usage_doc);

		return attrubuteMap;

	}

	public static void copyFile(String filePath, String dir) {
		Path sourceFile = Paths.get(filePath);
		Path targetDir = Paths.get(dir);
		Path targetFile = targetDir.resolve(sourceFile.getFileName());

		try {

			Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);

		} catch (FileAlreadyExistsException ex) {
			System.err.format("File %s already exists.", targetFile);
		} catch (IOException ex) {
			System.err.format("I/O Error when copying file");
			System.out.println(ex);
		}
	}

	public static String fetchGatewayURL(String cmdlineOutput) {

		String textNote[] = cmdlineOutput.split("\n");
		System.out.println("==================" + textNote.length);
		String output = null;
		for (String line : textNote) {
			if (line.startsWith("routes:")) {
				String lineStr[] = line.split(":");
				output = lineStr[1];
			}
		}

		String gatewayURL = (output.substring(12));
		System.out.println("==================" + gatewayURL);
		return gatewayURL;

	}

	public static void editGatewayFile(String path, Map map) throws IOException, ParseException {
		// Map map= getValueFromJsonFile("C:\\Automation\\test.json");

		File file = new File(path);

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("./ecagent_linux_sys -mod gateway \\");
		bw.append("\n");

		bw.append("-gpt");
		bw.append(" ");
		bw.append("8080");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-zon");
		bw.append(" ");
		bw.append(map.get("http-header-value").toString());
		bw.append(" \\");
		bw.append("\n");

		String completeServiceURI = (String) map.get("service-uri");
		int startIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "h", 0);
		int endIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "/", 2);
		String requiredServiceURI = completeServiceURI.substring(startIndex, endIndex);

		bw.append("-sst");
		bw.append(" ");
		bw.append(requiredServiceURI);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-tkn");
		bw.append(" ");
		bw.append(map.get("adm_tkn").toString());

		bw.close();

	}

	public static void editServerFile(String path, Map map, String gatewayURL, String clientID, String clientSecret)
			throws IOException, ParseException {
		// Map map= getValueFromJsonFile("C:\\Automation\\test.json");

		File file = new File(path);

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("./ecagent_windows_sys -mod server \\");
		bw.append("\n");

		System.out.println("Value of 'IDS' : " + map.get("ids"));
		String completeIDs = map.get("ids").toString();
		int start_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "[", 0);
		int separator_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, ",", 0);
		int end_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "]", 0);

		String first_ID = completeIDs.substring(start_IDIndex + 2, separator_IDIndex - 1);
		String second_ID = completeIDs.substring(separator_IDIndex + 2, end_IDIndex - 1);

		bw.append("-aid");
		bw.append(" ");
		bw.append(first_ID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-grp");
		bw.append(" ");
		bw.append(map.get("group_id").toString());
		bw.append(" \\");
		bw.append("\n");

		bw.append("-cid");
		bw.append(" ");
		bw.append(clientID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-csc");
		bw.append(" ");
		bw.append(clientSecret);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-dur");
		bw.append(" ");
		bw.append("3000");
		bw.append(" \\");
		bw.append("\n");

		String completeTrustedIssuerID = map.get("trustedIssuerIds").toString().replace("\\", "");
		int start_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "[", 0);
		int end_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "]", 0);
		String requiredTrustedIssuerID = completeTrustedIssuerID.substring(start_Index + 2, end_Index - 1);
		bw.append("-oa2");
		bw.append(" ");
		bw.append(requiredTrustedIssuerID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-hst");
		bw.append(" ");
		bw.append("wss://" + gatewayURL + "/agent");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-zon");
		bw.append(" ");
		bw.append(map.get("http-header-value").toString());
		bw.append(" \\");
		bw.append("\n");

		String completeServiceURI = (String) map.get("service-uri");
		int startIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "h", 0);
		int endIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "/", 2);
		String requiredServiceURI = completeServiceURI.substring(startIndex, endIndex);

		bw.append("-sst");
		bw.append(" ");
		bw.append(requiredServiceURI);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-rht");
		bw.append(" ");
		bw.append("localhost");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-rpt");
		bw.append(" ");
		bw.append("5432");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-hca");
		bw.append(" ");
		bw.append("8081");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-pxy");
		bw.append(" ");
		bw.append("http://PITC-Zscaler-ASPAC-Bangalore3PR.proxy.corporate.ge.com:80");

		bw.close();

	}

	public static void editServerBatchFile(String path, Map map, String gatewayURL, String clientID,
			String clientSecret) throws IOException, ParseException {
		// Map map= getValueFromJsonFile("C:\\Automation\\test.json");

		File file = new File(path);

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("ecagent_windows_sys -mod server");

		System.out.println("Value of 'IDS' : " + map.get("ids"));
		String completeIDs = map.get("ids").toString();
		int start_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "[", 0);
		int separator_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, ",", 0);
		int end_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "]", 0);

		String first_ID = completeIDs.substring(start_IDIndex + 2, separator_IDIndex - 1);
		String second_ID = completeIDs.substring(separator_IDIndex + 2, end_IDIndex - 1);

		bw.append(" ");
		bw.append("-aid");
		bw.append(" ");
		bw.append(first_ID);

		bw.append(" ");
		bw.append("-grp");
		bw.append(" ");
		bw.append(map.get("group_id").toString());

		bw.append(" ");
		bw.append("-cid");
		bw.append(" ");
		bw.append(clientID);

		bw.append(" ");
		bw.append("-csc");
		bw.append(" ");
		bw.append(clientSecret);

		bw.append(" ");
		bw.append("-dur");
		bw.append(" ");
		bw.append("3000");

		String completeTrustedIssuerID = map.get("trustedIssuerIds").toString().replace("\\", "");
		int start_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "[", 0);
		int end_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "]", 0);
		String requiredTrustedIssuerID = completeTrustedIssuerID.substring(start_Index + 2, end_Index - 1);
		bw.append(" ");
		bw.append("-oa2");
		bw.append(" ");
		bw.append(requiredTrustedIssuerID);

		bw.append(" ");
		bw.append("-hst");
		bw.append(" ");
		bw.append("wss://" + gatewayURL + "/agent");

		bw.append(" ");
		bw.append("-zon");
		bw.append(" ");
		bw.append(map.get("http-header-value").toString());

		String completeServiceURI = (String) map.get("service-uri");
		int startIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "h", 0);
		int endIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "/", 2);
		String requiredServiceURI = completeServiceURI.substring(startIndex, endIndex);

		bw.append(" ");
		bw.append("-sst");
		bw.append(" ");
		bw.append(requiredServiceURI);

		bw.append(" ");
		bw.append("-rht");
		bw.append(" ");
		bw.append("localhost");

		bw.append(" ");
		bw.append("-rpt");
		bw.append(" ");
		bw.append("5432");

		bw.append(" ");
		bw.append("-hca");
		bw.append(" ");
		bw.append("8081");

		bw.append(" ");
		bw.append("-pxy");
		bw.append(" ");
		bw.append("http://PITC-Zscaler-ASPAC-Bangalore3PR.proxy.corporate.ge.com:80");

		bw.close();

	}

	public static void editClientFile(String path, Map map, String gatewayURL, String clientID, String clientSecret)
			throws IOException, ParseException {
		// Map map= getValueFromJsonFile("C:\\Automation\\test.json");

		File file = new File(path);

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("./ecagent_windows_sys -mod client \\");
		bw.append("\n");

		System.out.println("Value of 'IDS' : " + map.get("ids"));
		String completeIDs = map.get("ids").toString();
		int start_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "[", 0);
		int separator_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, ",", 0);
		int end_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "]", 0);

		String first_ID = completeIDs.substring(start_IDIndex + 2, separator_IDIndex - 1);
		String second_ID = completeIDs.substring(separator_IDIndex + 2, end_IDIndex - 1);

		bw.append("-aid");
		bw.append(" ");
		bw.append(second_ID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-tid");
		bw.append(" ");
		bw.append(first_ID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-grp");
		bw.append(" ");
		bw.append(map.get("group_id").toString());
		bw.append(" \\");
		bw.append("\n");

		bw.append("-cid");
		bw.append(" ");
		bw.append(clientID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-csc");
		bw.append(" ");
		bw.append(clientSecret);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-dur");
		bw.append(" ");
		bw.append("3000");
		bw.append(" \\");
		bw.append("\n");

		String completeTrustedIssuerID = map.get("trustedIssuerIds").toString().replace("\\", "");
		int start_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "[", 0);
		int end_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "]", 0);
		String requiredTrustedIssuerID = completeTrustedIssuerID.substring(start_Index + 2, end_Index - 1);
		bw.append("-oa2");
		bw.append(" ");
		bw.append(requiredTrustedIssuerID);
		bw.append(" \\");
		bw.append("\n");

		bw.append("-hst");
		bw.append(" ");
		bw.append("wss://" + gatewayURL + "/agent");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-lpt");
		bw.append(" ");
		bw.append("7996");
		bw.append(" \\");
		bw.append("\n");

		bw.append("-pxy");
		bw.append(" ");
		bw.append("http://PITC-Zscaler-ASPAC-Bangalore3PR.proxy.corporate.ge.com:80");

		bw.close();

	}

	public static void editClientBatchFile(String path, Map map, String gatewayURL, String clientID,
			String clientSecret, String lpt) throws IOException, ParseException {
		// Map map= getValueFromJsonFile("C:\\Automation\\test.json");

		File file = new File(path);

		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("ecagent_windows_sys -mod client");

		System.out.println("Value of 'IDS' : " + map.get("ids"));
		String completeIDs = map.get("ids").toString();
		int start_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "[", 0);
		int separator_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, ",", 0);
		int end_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "]", 0);

		String first_ID = completeIDs.substring(start_IDIndex + 2, separator_IDIndex - 1);
		String second_ID = completeIDs.substring(separator_IDIndex + 2, end_IDIndex - 1);
		bw.append(" ");
		bw.append("-aid");
		bw.append(" ");
		bw.append(second_ID);

		bw.append(" ");
		bw.append("-tid");
		bw.append(" ");
		bw.append(first_ID);

		bw.append(" ");
		bw.append("-grp");
		bw.append(" ");
		bw.append(map.get("group_id").toString());

		bw.append(" ");
		bw.append("-cid");
		bw.append(" ");
		bw.append(clientID);

		bw.append(" ");
		bw.append("-csc");
		bw.append(" ");
		bw.append(clientSecret);

		bw.append(" ");
		bw.append("-dur");
		bw.append(" ");
		bw.append("3000");

		String completeTrustedIssuerID = map.get("trustedIssuerIds").toString().replace("\\", "");
		int start_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "[", 0);
		int end_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "]", 0);
		String requiredTrustedIssuerID = completeTrustedIssuerID.substring(start_Index + 2, end_Index - 1);
		bw.append(" ");
		bw.append("-oa2");
		bw.append(" ");
		bw.append(requiredTrustedIssuerID);

		bw.append(" ");
		bw.append("-hst");
		bw.append(" ");
		bw.append("wss://" + gatewayURL + "/agent");

		bw.append(" ");
		bw.append("-lpt");
		bw.append(" ");
		bw.append(lpt);

		bw.append(" ");
		bw.append("-pxy");
		bw.append(" ");
		bw.append("http://PITC-Zscaler-ASPAC-Bangalore3PR.proxy.corporate.ge.com:80");

		bw.close();

	}

	public static void run_shFile(String shFilePath) {
		Process p;
		try {
			String[] cmd = { "C:\\Program Files\\Git\\bin\\sh", shFilePath };
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void run_batFile(String batFilePath) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("cmd /c server.bat", null, new File(batFilePath));
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void run_batFileenhancement(String command, String batFilePath, String expValue) throws InterruptedException {
		Process p;
		try {
			p = Runtime.getRuntime().exec(command, null, new File(batFilePath));
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			String consoleOp = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				consoleOp = consoleOp + line + "\n";
				//Thread.sleep(1000);
				if (line.contains(expValue)) {
					break;
				}
			}

			// System.out.println("console Output "+consoleOp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	
	public static void main(String[] args) throws IOException, JSONException, ParseException {
		// TODO Auto-generated method stub

		String uaa_secret_FilePath = "C:\\Users\\sareddyc\\Automation\\uaa-secret.json";
		String uaa_issuerid_FilePath = "C:\\Users\\sareddyc\\Automation\\uaa-issuerid.json";
		String VCAP_FilePath = "C:\\Users\\sareddyc\\Automation\\VCAP.json";
		String gateway_FilePath = "C:\\Users\\sareddyc\\Automation\\gateway.sh";

		String jsonStringServiceKeyOutput = "{\r\n"
				+ "  \"dashboardUrl\": \"https://uaa-dashboard.run.aws-usw02-dev.ice.predix.io/#/login/5da5a45c-7869-44ac-a8a1-9ed70b9919ea\",\r\n"
				+ "  \"issuerId\": \"https://5da5a45c-7869-44ac-a8a1-9ed70b9919ea.predix-uaa.run.aws-usw02-dev.ice.predix.io/oauth/token\",\r\n"
				+ "  \"subdomain\": \"5da5a45c-7869-44ac-a8a1-9ed70b9919ea\",\r\n"
				+ "  \"uri\": \"https://5da5a45c-7869-44ac-a8a1-9ed70b9919ea.predix-uaa.run.aws-usw02-dev.ice.predix.io\",\r\n"
				+ "  \"zone\": {\r\n" + "    \"http-header-name\": \"X-Identity-Zone-Id\",\r\n"
				+ "    \"http-header-value\": \"5da5a45c-7869-44ac-a8a1-9ed70b9919ea\"\r\n" + "  }\r\n" + "}";

		createJsonCredential(uaa_secret_FilePath, "password");
		appendServiceKeyOutputToJsonCredential(uaa_secret_FilePath, jsonStringServiceKeyOutput);
		String issuerId = getIssuerId(jsonStringServiceKeyOutput);
		System.out.println("issuerId:" + issuerId);

		createJsonFileTrustedIssuerID(uaa_issuerid_FilePath, issuerId);

		System.out.println("-----Fetching values from UAA Secret File-----");
		String adminClientSecret = getKeyValueFromUaa_Secret(uaa_secret_FilePath, "adminClientSecret");
		System.out.println("Value of key 'adminClientSecret' is " + adminClientSecret);
		String dashboardURL = getKeyValueFromUaa_Secret(uaa_secret_FilePath, "dashboardUrl");
		System.out.println("Value of key 'dashboardUrl' is " + dashboardURL);

		Map map = getKeyValueFromVCAPFile(VCAP_FilePath);
		System.out.println("*****Fetching VCAP Values*****");
		System.out.println("Value of 'Admin Token' : " + map.get("adm_tkn"));
		System.out.println("Value of 'Group ID' : " + map.get("group_id"));

		// Trusted Issuer ID
		System.out
				.println("Value of 'Trusted Issuer ID' : " + map.get("trustedIssuerIds").toString().replace("\\", ""));
		String completeTrustedIssuerID = map.get("trustedIssuerIds").toString().replace("\\", "");
		int start_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "[", 0);
		int end_Index = JsonTesting.ordinalIndexOf(completeTrustedIssuerID, "]", 0);
		String requiredTrustedIssuerID = completeTrustedIssuerID.substring(start_Index + 2, end_Index - 1);
		System.out.println("Required Trusted Issuer ID : " + requiredTrustedIssuerID);

		// Service URI
		System.out.println("Value of 'Service URI' : " + map.get("service-uri"));
		String completeServiceURI = (String) map.get("service-uri");
		System.out.println("Complete Service URI: " + completeServiceURI);
		int startIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "h", 0);
		int endIndex = JsonTesting.ordinalIndexOf(completeServiceURI, "/", 2);
		String requiredServiceURI = completeServiceURI.substring(startIndex, endIndex);
		System.out.println("Required Service URI: " + requiredServiceURI);

		System.out.println("Value of 'Usage Doc' : " + map.get("usage-doc"));
		System.out.println("Value of 'HTTP Header Name' : " + map.get("http-header-name"));
		System.out.println("Value of 'HTTP Header Value' : " + map.get("http-header-value"));
		System.out.println("Value of 'Oauth Scope' : " + map.get("oauth-scope"));

		// ID's
		System.out.println("Value of 'IDS' : " + map.get("ids"));
		String completeIDs = map.get("ids").toString();
		int start_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "[", 0);
		int separator_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, ",", 0);
		int end_IDIndex = JsonTesting.ordinalIndexOf(completeIDs, "]", 0);

		String first_ID = completeIDs.substring(start_IDIndex + 2, separator_IDIndex - 1);
		String second_ID = completeIDs.substring(separator_IDIndex + 2, end_IDIndex - 1);
		System.out.println("First ID : " + first_ID);
		System.out.println("Second ID : " + second_ID);

		// Updating gateway.sh file

	}

	public static void sleep() {
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
