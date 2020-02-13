package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EC_Configuration extends BaseTest_EnterpriseConnect {

	@Parameters("folderName")
	@Test
	public void ec_config(String folderName) throws IOException {

		// Create folder structure
		createFolderStructure(folderName, "Binaries");
		createFolderStructure(folderName, "Gateway");
		createFolderStructure(folderName, "Server");
		createFolderStructure(folderName, "Client");

		// Move cf3/cf1 binaries to the respective folders based on our requirement
		copyFile(windowsSrcFile, new File(directoryPath + folderName + "\\Binaries\\ecagent_windows_sys"));
		copyFile(linuxSrcFile, new File(directoryPath + folderName + "\\Binaries\\ecagent_linux_sys"));

		/*
		 * moveFile(windowsBinaryPath, directoryPath + folderName +
		 * "\\Binaries\\ecagent_windows_sys"); moveFile(linuxBinaryPath, directoryPath +
		 * folderName + "\\Binaries\\ecagent_linux_sys"); moveFile(linuxBinaryPath,
		 * directoryPath + folderName + "\\Gateway\\ecagent_linux_sys");
		 * moveFile(windowsBinaryPath, directoryPath + folderName +
		 * "\\Server\\ecagent_windows_sys"); moveFile(windowsBinaryPath, directoryPath +
		 * folderName + "\\Client\\ecagent_windows_sys");
		 */
		// Create JSON files

	}

	public void ecsetup() {
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

			String cmdResult = strBuilder.toString();
			System.out.println(cmdResult);
			
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
	}

}
