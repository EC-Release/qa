package com.ge.corp.DigitalConnect.ec.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ge.corp.DigitalConnect.ec.util.TestUtil;
import com.ge.corp.DigitalConnect.ec_automationpoc.scripts.JsonTesting;
import com.ge.corp.DigitalConnect.ec_automationpoc.scripts.TestBase;

public class UaaSDashboardPage_Objects extends TestBase{
	
	//Object Repository
	@FindBy(xpath= "//a[text()='Client Management']")
	WebElement client_Management;
	
	@FindBy(xpath = "//div[@class='title-bar__actions flex flex--spaced style-scope px-title-bar']//span[text()='Create Client']")
	WebElement create_Client_Titlebar;
	
	@FindBy(xpath = "//uaa-side-nav[@id='sideNavClient']//span[text()='Create Client']")
	WebElement create_Client_Sidebar;
	
	@FindBy(xpath = "//uaa-client-form[@id='newClientPage']//input[@id='clientIdInput-input']")
	WebElement client_ID;
	
	@FindBy(xpath = "//uaa-client-form[@id='newClientPage']//uaa-input-container[@id='authorizedGrantTypesInput']//span[text()='refresh_token']")
	WebElement refresh_Token;
	
	@FindBy(xpath = "//uaa-client-form[@id='newClientPage']//input[@id='clientSecretInput-input']")
	WebElement client_Secret;
	
	@FindBy(xpath = "//uaa-client-form[@id='newClientPage']//input[@id='clientSecretInput-input-2']")
	WebElement confirm_ClientSecret;
	
	@FindBy(xpath = "//uaa-client-form[@id='newClientPage']//uaa-tag-search[@id='authoritiesInput']//input[@id='inputField']")
	WebElement authorities;
	
	@FindBy(xpath = "//uaa-client-form[@id='newClientPage']//button[text()='Save']")
	WebElement save_Button;
	
	@FindBy(xpath = "//*[@id='sideNavClient']//span")
	WebElement client_Name;
	
	//Initializing the page objects
	public UaaSDashboardPage_Objects() {
		PageFactory.initElements(driver, this);
	}
	
	public void createUAACLient(String clientID, String clientSecret, String confirmClientSecret, String authScope) {
		System.out.println("Click on Client Management");
		client_Management.click();
		JsonTesting.waitForElementToBeClickable(driver, create_Client_Titlebar);
		System.out.println("Click on Create Client");
		create_Client_Titlebar.click();
		client_ID.sendKeys(clientID);
		refresh_Token.click();
		client_Secret.sendKeys(clientSecret);
		confirm_ClientSecret.sendKeys(confirmClientSecret);
		authorities.sendKeys(authScope);
		save_Button.click();
		System.out.println("UAA Client Created Successfully : " + clientID);
		
	}
	
	
	public void createUAACLient1(String clientID, String clientSecret, String authScope) {
		try {
			TestUtil.findElementToClick(client_Management, "Client Management");
			System.out.println("Clicked on 'Client Management'");
			
			JsonTesting.waitForElementToBeClickable(driver, create_Client_Titlebar);
			TestUtil.findElementToClick(create_Client_Titlebar,"Create Client");
			System.out.println("Clicked on 'Create Client'");
			
			TestUtil.findElementTextbox(client_ID, clientID, "Client ID");
			System.out.println(clientID + " Entered on 'Client ID' Text Field");
			
			TestUtil.findElementToClick(refresh_Token,"Refresh Token");
			System.out.println("Selected 'Refresh Token'");		
			
			TestUtil.findElementTextbox(client_Secret, clientSecret, "Client Secret");
			System.out.println(clientSecret + " Entered on 'Client Secret' Text Field");
			
			TestUtil.findElementTextbox(confirm_ClientSecret, clientSecret, "Confirm Client Secret");
			System.out.println(clientSecret + " Entered on 'Confirm Client Secret' Text Field");
			
			TestUtil.findElementTextbox(authorities, authScope, "Authorities");
			System.out.println(authScope + " Entered on 'Authorities' Text Field");
			
			TestUtil.findElementToClick(save_Button, "Save");
			System.out.println("Clicked on 'Save'");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	



}
