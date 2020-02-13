package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.IOException;

import org.json.JSONException;

import com.ge.corp.DigitalConnect.ec.util.ExcelHandler;

public class CreateUAATrustedIssuerID {
	JsonTesting jsonTesting;
	ExcelHandler xlHandler;

	public void create_UAAIssuerIDFile() throws IOException, JSONException {
		RetrieveUAAServiceKeyInfo retrieveUAAServicekeyInfo = new RetrieveUAAServiceKeyInfo();
		String uaaServicekeyInfo = retrieveUAAServicekeyInfo.retrieve_UAA_Service_Info();
		String requiredResult = uaaServicekeyInfo.substring(uaaServicekeyInfo.indexOf('{'));
		JsonTesting
				.createJsonFileTrustedIssuerID(
						xlHandler.getData("DirectoryPath", "Value") + xlHandler.getData("MainFolderName", "Value")
								+ "\\\\" + xlHandler.getData("UAA_IssuerID_FileName", "Value"),
						JsonTesting.getIssuerId(requiredResult));
	}

}
