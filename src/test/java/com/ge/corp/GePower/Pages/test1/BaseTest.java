package com.ge.corp.GePower.Pages.test1;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.webdriver.BaseWebDriver;
import com.ge.digital.itops.webdriver.WebDriverAction;

public class BaseTest extends BaseWebDriver {

	@Parameters({ "url" })
	@BeforeClass
	public void setbrowse(String url) throws Exception {
		BrowserAction.openBrowser(url);		
	}

	@AfterClass
	public void quitbrowser() throws Exception {		
		BrowserAction.closeBrowser();
	}
	

}
