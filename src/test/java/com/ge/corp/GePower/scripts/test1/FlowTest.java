package com.ge.corp.GePower.scripts.test1;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.annotations.*;
import com.ge.corp.GePower.Objects.test1.Flow_Test_Objects;
import com.ge.corp.GePower.Pages.test1.AnotherBusiness;
import com.ge.corp.GePower.Pages.test1.BaseTest;
import com.ge.corp.GePower.Pages.test1.Flow_Test_LoginPage;
import com.ge.corp.GePower.Pages.test1.ReadExcel;
import com.ge.digital.itops.browser.BrowserAccess;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.browser.BrowserAssert;
import com.ge.digital.itops.browser.BrowserWait;
import com.ge.digital.itops.testng.Actual;
import com.ge.digital.itops.testng.Documentation;
import com.ge.digital.itops.testng.FailureMessage;
import com.ge.digital.itops.testng.PDFReporter;
import com.ge.digital.itops.webdriver.WebDriverAction;
import com.ge.digital.itops.webdriver.WebDriverTable;
import com.ge.digital.itops.webdriver.WebDriverWaits;
import com.sun.jna.platform.unix.X11.Window;
import com.ge.digital.itops.webdriver.BaseWebDriver;
import com.ge.digital.itops.webdriver.WebDriverAccess;


public class FlowTest extends BaseTest {
	
	
	static WebDriverWait  wait=null;
	String password1=null;
	String username1=null;
	@Parameters({"username","password"})
	@Test(priority = 1)
	@Documentation(step = "Login with proper valid credentials.", expected = "HomePage should be display.")
	@Actual(actual = "User is able to see home page.")
	@FailureMessage("User is not able to see page due to some issue.")	
	public void Verify_Login_functionality(String username,String password) throws Exception 
	{				
		
		Flow_Test_LoginPage.enterCredentials(username,password);
		wait = new WebDriverWait(driver,200);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Home Tab - Selected']")));		
		BrowserAssert.assertElementIsDisplayed(Flow_Test_Objects.home_page_XPATH);		
	}
	
	@Test(priority=2)
	@Documentation(step = "Click on Save & Add Product", expected = "Validation should be displayed on Save without mandatory fields")
	@Actual(actual = "Valdiation message displayed on Save with no values")
	@FailureMessage("Validation message not displayed on Save with no values")
	public void check_sorting_functionality() throws Exception
	{
		BrowserAction.getElement(Flow_Test_Objects.account_tab_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.lastpage_icon_XPATH, 30);
		//BrowserAction.getElement(Flow_Test_Objects.lastpage_icon_XPATH).click();
		Flow_Test_LoginPage.sorting();
	}
	
	@Test(priority = 21)
	@Documentation(step = "Click on Save & Add Product", expected = "Validation should be displayed on Save without mandatory fields")
	@Actual(actual = "Valdiation message displayed on Save with no values")
	@FailureMessage("Validation message not displayed on Save with no values")
	public void Create_New_Oppty_Validation() throws Exception
	{
		BrowserAction.getElement(Flow_Test_Objects.opportunity_tab_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.new_oppty_btn_XPATH, 20);
		BrowserAction.getElement(Flow_Test_Objects.new_oppty_btn_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.continue_btn_XPATH, 30);
		BrowserAction.getElement(Flow_Test_Objects.continue_btn_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.save_oppty_btn_XPATH, 30);
		BrowserAction.getElement(Flow_Test_Objects.save_oppty_btn_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.validation_msg_on_save_XPATH, 30);
		BrowserAssert.assertElementIsDisplayed(Flow_Test_Objects.validation_msg_on_save_XPATH);
	}
	
	@Test(priority = 3,dataProvider="BusinessData")
	@Documentation(step = "Create New Oppty on Save & Add Product", expected = "New Oppty should be created")
	@Actual(actual = "New Oppty created successfully")
	@FailureMessage("New Oppty not created")
	public void Create_New_Oppty_functionality(String OpptyName,String AccountName) throws Exception
	{
		BrowserAction.getElement(Flow_Test_Objects.opportunity_name_input_XPATH).clear();
		BrowserAction.getElement(Flow_Test_Objects.opportunity_name_input_XPATH).sendKeys(OpptyName);
		String parentWindow=WebDriverAccess.getWindowHandle();
		BrowserAction.getElement(Flow_Test_Objects.account_name_search_icon_XPATH).click();
		Set<String> all_windows=WebDriverAccess.getWindowHandles();
		for(String handle:all_windows)
		{
			if(!handle.equals(parentWindow))
			{
				WebDriverAction.switchTo(handle);
				BrowserAction.switchToFrame("searchFrame");
				BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.search_account_input_ID, 30);
				BrowserAction.getElement(Flow_Test_Objects.search_account_input_ID).clear();
				BrowserAction.getElement(Flow_Test_Objects.search_account_input_ID).sendKeys(AccountName);
				BrowserAction.getElement(Flow_Test_Objects.go_btn_XPATH).click();
				Thread.sleep(3000);			
				BrowserAction.switchToDefaultContent();
				BrowserAction.switchToFrame("resultsFrame");
				WebDriverAction.getElement(By.xpath("//div[@class='lookupSearch']//a[text()='"+AccountName+"']")).click();
				Thread.sleep(3000);				
			}
		}
		WebDriverAction.switchTo(parentWindow);
		BrowserAction.getElement(Flow_Test_Objects.estimated_order_date_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.select_today_date_XPATH, 20);
		BrowserAction.getElement(Flow_Test_Objects.select_today_date_XPATH).click();
		BrowserAction.getElement(Flow_Test_Objects.oppty_step_label_XPATH).click();
		BrowserAction.selectDropdownOptionByText(Flow_Test_Objects.oppty_setup_drodpown_XPATH, "1. Understand Customer");
		BrowserAction.selectDropdownOptionByText(Flow_Test_Objects.order_forecast_dropdown_XPATH, "Not addressable");
		//Select oppty_step_DP=new Select(driver.findElement(By.xpath("//label[text()='Opportunity Step']//ancestor::td[1]//following-sibling::td[1]//select")));
		//Select oppty_step_DP=new Select(Web)
		//oppty_step_DP.selectByVisibleText("Understand Customer");
		//Select order_forecast_DP=new Select(driver.findElement(By.xpath("//label[text()='Order Forecast Category']//ancestor::td[1]//following-sibling::td[1]//select")));
		//order_forecast_DP.selectByVisibleText("Not addressable");
		//BrowserAction.getElement(Flow_Test_Objects.opportunity_step_XPATH).click();
		BrowserAction.getElement(Flow_Test_Objects.save_oppty_btn_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.save_portfolio_btn_XPATH, 100);
		BrowserAction.getElement(Flow_Test_Objects.save_portfolio_btn_XPATH).click();		
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.cancel_btn_XPATH, 100);
		BrowserAction.getElement(Flow_Test_Objects.cancel_btn_XPATH).click();
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.edit_btn_XPATH, 200);
		BrowserAssert.assertElementIsDisplayed(Flow_Test_Objects.edit_btn_XPATH);		
	}
	
	@DataProvider(name="BusinessData")
	public Object[][] getBusinessData() throws Exception{
		
		//String path;
		//String name;
		Object[][] data =  ReadExcel.readExcelDatatoMap("C:\\Users\\saupraja\\Documents\\My Received Files\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\main\\java\\com\\Excelfile", "Book1.xlsx","Sheet3",2);
	    return data;
	}

}
