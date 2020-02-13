package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

public class BaseTest_EnterpriseConnect {

	String directoryPath = "C:\\users\\sareddyc\\SampleEC\\";
	String uaa_secret_path = "C:\\users\\sareddyc\\SampleEC\\uaa-secret.json";
	String uaa_trustedIssuerId_path = "C:\\users\\sareddyc\\SampleEC\\uaa-issuerid.json";
	String VCAP_path = "C:\\users\\sareddyc\\SampleEC\\VCAP.json";

	File windowsSrcFile = new File("C:\\Users\\sareddyc\\cfBinaries\\ecagent_windows_sys");
	File linuxSrcFile = new File("C:\\\\Users\\\\sareddyc\\\\cfBinaries\\\\ecagent_linux_sys");

	// Create a directory and its sub directories
	public void createFolderStructure(String directoryName, String subDirectoryName) {

		File files = new File(directoryPath + directoryName + "\\" + subDirectoryName);
		if (!files.exists()) {
			if (files.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
	}

	// Move File
	public void moveFile(String src, String dest) {
		Path result = null;
		try {
			result = Files.move(Paths.get(src), Paths.get(dest));
		} catch (IOException e) {
			System.out.println("Exception while moving file: " + e.getMessage());
		}
		if (result != null) {
			System.out.println("File moved successfully.");
		} else {
			System.out.println("File movement failed.");
		}
	}

	// Copy FIle
	public void copyFile(File srcFile, File destFile) throws IOException {
		FileUtils.copyFile(srcFile, destFile);
	}

	// Create & Write JSON file
	public void create_write_jsonFile(String filePath, String jsonFileName) {
		// Creating a JSONObject object
		JSONObject jsonObjectt = new JSONObject();
		// Inserting key-value pairs into the json object
		jsonObjectt.put("adminCLientSecret", "password");

		try {
			FileWriter file = new FileWriter(filePath + jsonFileName + ".json");
			file.write(jsonObjectt.toJSONString());
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("JSON file created: " + jsonObjectt);
	}

	public String executeCommandFromCommandPrompt(String commandToExecute) {
		String cmdResult = null;
		try {
			String[] command = new String[3];
			command[0] = "cmd";
			command[1] = "/c";
			command[2] = "cd c:\\ && cd users && cd sareddyc && cd EC HandsonSetup && set https_proxy=http://3.28.29.241:88/ && cf login -a https://api.system.aws-usw02-dev.ice.predix.io -u prasad_alokam@hotmail.com -p eC7eamR0ck! -o enterprise-connect -s Dev && cf create-service predix-uaa Free ec-handsonsetup3-uaa -c uaa-secret.json && cf csk  ec-handsonsetup3-uaa ec-handsonsetup-uaaservicekey3 && cf service-key  ec-handsonsetup3-uaa ec-handsonsetup-uaaservicekey3";

			Process p = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = reader.readLine();

			StringBuilder strBuilder = new StringBuilder();
			while (line != null) {
				// System.out.println(line);
				line = reader.readLine();
				strBuilder.append(line).append("\n");
			}

			cmdResult = strBuilder.toString();
			System.out.println(cmdResult);
			/*
			 * Pattern pattern = Pattern.compile("\{(.*?)\}"); Matcher matcher =
			 * pattern.matcher(cmdResult); if(matcher.find()) {
			 * System.out.println(matcher.group(1)); }
			 */

			String extractedText = StringUtils.substringBetween(cmdResult, "{", "}");
			System.out.println(extractedText);

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
		return cmdResult;
	}

}
