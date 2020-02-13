package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.IOException;

import org.json.JSONException;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class AppendUAASecretFileWithUAAServiceKeyInfo {
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;

	public void update_UAASecretFile() throws IOException, JSONException {
		RetrieveUAAServiceKeyInfo retrieveUAAServicekeyInfo = new RetrieveUAAServiceKeyInfo();
		String uaaServicekeyInfo = retrieveUAAServicekeyInfo.retrieve_UAA_Service_Info();
		String requiredResult = uaaServicekeyInfo.substring(uaaServicekeyInfo.indexOf('{'));
		JsonTesting.appendServiceKeyOutputToJsonCredential(xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value") + "\\\\"
				+ xlHandler.getData("UAASecretFileName", "Value"), requiredResult);
	}

}
