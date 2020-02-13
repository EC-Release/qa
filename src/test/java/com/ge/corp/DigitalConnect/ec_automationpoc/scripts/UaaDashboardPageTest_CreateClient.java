package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import com.ge.corp.DigitalConnect.ec.objects.UaaSDashboardPage_Objects;

public class UaaDashboardPageTest_CreateClient {

	UaaSDashboardPage_Objects uaaDashboardPage;

	public UaaDashboardPageTest_CreateClient() {
		super();
	}

	public void createClient(String clientID, String clientSecret, String confirmClientSecret, String authScope) {
		uaaDashboardPage = new UaaSDashboardPage_Objects();
		uaaDashboardPage.createUAACLient(clientID, clientSecret, confirmClientSecret, authScope);
	}
	
	public void createClient1(String clientID, String clientSecret, String authScope) {
		uaaDashboardPage = new UaaSDashboardPage_Objects();
		uaaDashboardPage.createUAACLient1(clientID, clientSecret, authScope);
	}

}
