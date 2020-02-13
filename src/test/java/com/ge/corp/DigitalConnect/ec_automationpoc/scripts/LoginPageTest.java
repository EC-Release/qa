package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.IOException;

import org.json.JSONException;

import com.ge.corp.DigitalConnect.ec.objects.LoginPage_Objects;
import com.ge.corp.DigitalConnect.ec.objects.UaaSDashboardPage_Objects;

public class LoginPageTest extends TestBase{
	
	LoginPage_Objects loginPage;
	UaaSDashboardPage_Objects uaaDashboardPage;
	
	public LoginPageTest() {
		super();
	}
	
	public void setup(String url) throws IOException, JSONException, InterruptedException {
		initialization(url);
		loginPage = new LoginPage_Objects();
	}
	
	public void loginTest() throws IOException, JSONException {
		uaaDashboardPage = loginPage.login(JsonTesting.getKeyValueFromUaa_Secret(uaa_secret_path, "adminClientSecret"));
		
	}
	
	public void loginTestExisting(String pwd) throws IOException, JSONException {
		uaaDashboardPage = loginPage.login(pwd);
	}

}
