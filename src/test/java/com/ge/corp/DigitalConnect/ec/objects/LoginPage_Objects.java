package com.ge.corp.DigitalConnect.ec.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ge.corp.DigitalConnect.ec_automationpoc.scripts.JsonTesting;
import com.ge.corp.DigitalConnect.ec_automationpoc.scripts.TestBase;

public class LoginPage_Objects extends TestBase {

	// Object Repository
	@FindBy(id = "client-secret")
	WebElement password;

	@FindBy(id = "loginButton")
	WebElement loginButton;

	// Initializing the page objects
	public LoginPage_Objects() {
		PageFactory.initElements(driver, this);
	}

	public UaaSDashboardPage_Objects login(String pwd) {

		// password.sendKeys(jsont.getKeyValueFromUaa_Secret(uaa_secret_path,
		// "adminClientSecret"));
		password.sendKeys(pwd);
		loginButton.click();

		return new UaaSDashboardPage_Objects();
	}

}
