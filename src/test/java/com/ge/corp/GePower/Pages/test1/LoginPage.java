package com.ge.corp.GePower.Pages.test1;

import com.ge.corp.GePower.Objects.test1.LoginPageObject;
import com.ge.digital.itops.browser.BrowserAccess;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.browser.BrowserAssert;
import com.ge.digital.itops.browser.BrowserWait;
import com.ge.digital.itops.webdriver.BaseWebDriver;
import com.ge.digital.itops.webdriver.WebDriverWaits;
//import com.ge.corp.GePower.Pages.test1.*;

public class LoginPage extends BaseWebDriver
{
	public static void enterCredential(String username, String password) throws Exception
	{
		BrowserWait.waitUntilElementIsEnabled(LoginPageObject.username_ID);
		BrowserWait.waitUntilElementIsEnabled(LoginPageObject.password_ID);
		BrowserAction.getElement(LoginPageObject.username_ID).clear();	
		BrowserAction.enterFieldValue(LoginPageObject.username_ID, username);
		String test_username=BrowserAccess.getElementAttributeValue(LoginPageObject.username_ID, "value");
		System.out.println(test_username);
		BrowserAction.getElement(LoginPageObject.password_ID).clear();
		BrowserAction.enterFieldValue(LoginPageObject.password_ID, password);	
		BrowserAssert.assertElementIsDisplayed(LoginPageObject.loginbtn_ID);
		BrowserAction.click(LoginPageObject.loginbtn_ID);
		
	}
	
	/*public void clickLoginButton() throws Exception
	{
		BrowserAction.click(LoginPageObject.loginbtn_ID);
	}*/
	
	public static void EnterProfile() throws Exception
	{
		BrowserWait.waitUntilElementIsEnabled(LoginPageObject.Profile_XPATH);
		BrowserAssert.assertElementIsDisplayed(LoginPageObject.Profile_XPATH);
		BrowserAction.click(LoginPageObject.Profile_XPATH);
		BrowserAction.click(LoginPageObject.Save_XPATH);
	}
	

	/*public void SaveProfile() throws Exception
	{
		Thread.sleep(10000);

		
	}*/
	
	public static void Saveok() throws Exception
	{
		//Thread.sleep(10000);
		BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ok_XPATH);
		BrowserAssert.assertElementIsDisplayed(LoginPageObject.ok_XPATH);
		BrowserAction.click(LoginPageObject.ok_XPATH);
	}
	

	public static void CreateChange() throws Exception
	{
		//Thread.sleep(10000);
		BrowserWait.waitUntilElementIsEnabled(LoginPageObject.Create_XPATH);
		BrowserAssert.assertElementIsDisplayed(LoginPageObject.Create_XPATH);
		BrowserAction.click(LoginPageObject.Create_XPATH);
	}

	
	
	
	/*public void clicksite() throws Exception
	{
		BrowserAction.click(LoginPageObject.siteclick_LINK);
	}*/
	
	
}