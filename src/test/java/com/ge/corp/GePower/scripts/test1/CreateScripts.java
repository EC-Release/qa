package com.ge.corp.GePower.scripts.test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ge.corp.GePower.Objects.test1.LoginPageObject;
import com.ge.corp.GePower.Pages.test1.AnotherBusiness;
import com.ge.corp.GePower.Pages.test1.BaseTest;
import com.ge.corp.GePower.Pages.test1.LoginPage;
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
import com.ge.digital.itops.webdriver.BaseWebDriver;

public class CreateScripts extends BaseTest{
	
	static AnotherBusiness another;
	static WebDriverWait  wait=null;
	
	@Test(priority = 1,enabled=false)
	@Documentation(step = "Login As Admin with proper valid credentials.", expected = "HomePage should be display.")
	@Actual(actual = "User is able to see home page.")
	@FailureMessage("User is not able to see page due to some issue.")
	public void login_To_VisitorManager() throws Exception {
		LoginPage.enterCredential("503115288", "VI1995neet");		
		BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.GetConfirm_XPATH);
		BrowserAssert.assertElementIsDisplayed(LoginPageObject.GetConfirm_XPATH);
		BrowserAction.click(LoginPageObject.GetConfirm_XPATH);		
		wait = new WebDriverWait(driver,20 );
		
	}
	
	@Test(priority = 2,enabled=false)
	@Documentation(step = "Click on Settings.", expected = "Should fill the details .")
	@Actual(actual = "User is able to see the Profile page.")
	@FailureMessage("User is not able to see page due to some issue.")
	public void clickToProfile() throws Exception {
		BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.Profile_XPATH);
		BrowserAssert.assertElementIsDisplayed(LoginPageObject.Profile_XPATH);
		BrowserAction.click(LoginPageObject.Profile_XPATH);
		Thread.sleep(6000);
	}
	
	@Test(priority = 3 ,dataProvider="BusinessData",enabled=false)
	@Documentation(step = "Click on Save.", expected = "Request successfully Submitted .")
	@Actual(actual = "User is able to Create the Profile Successfully")
	@FailureMessage("User is not able to see page due to some issue.")
	public void clickTosave(String Business,String organization,String sub_organization,String region,String country,String Location_Site ) throws Exception {
		
		
			System.out.println(" "+Business+" "+organization+" "+sub_organization+" "+region+" "+country+" "+Location_Site);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickBusiness_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickBusiness_XPATH);
			BrowserAction.click(LoginPageObject.ClickBusiness_XPATH);
			//Thread.sleep(2000);
			
			String tempXpath = "//*[@id='contentWrapper']//*[@role='option' and text()='"+Business+"']";
			System.out.println("tempXpath: "+tempXpath);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tempXpath)));
			WebDriverAction.getDriver().findElement(By.xpath(tempXpath)).click();
			//Thread.sleep(2000);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickOrganisation_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickOrganisation_XPATH);
			BrowserAction.click(LoginPageObject.ClickOrganisation_XPATH);
			//Thread.sleep(3000);
			
			String tempXpath1 = "//*[@id='contentWrapper']//*[@role='option' and text()='"+organization+"']";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tempXpath1)));
			WebDriverAction.getDriver().findElement(By.xpath(tempXpath1 )).click();
			System.out.println("tempXpath: "+tempXpath1 );
			Thread.sleep(2000);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickSubOrganisation_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickSubOrganisation_XPATH);
			BrowserAction.click(LoginPageObject.ClickSubOrganisation_XPATH);
			Thread.sleep(2000);
			
			String tempXpath2 = "//*[@id='contentWrapper']//*[@role='option' and text()='"+sub_organization+"']";
			System.out.println("tempXpath: "+tempXpath2);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tempXpath2)));
			WebDriverAction.getDriver().findElement(By.xpath(tempXpath2)).click();
			//Thread.sleep(3000);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickRegion_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickRegion_XPATH);
			BrowserAction.click(LoginPageObject.ClickRegion_XPATH);
			//Thread.sleep(3000);
			
			String tempXpath3 = "(//*[@id='profRegId']//*[@id='dropdown']//*[@id='contentWrapper']//*[@role='option' and text()='"+region+"'])";
			System.out.println("tempXpath: "+tempXpath3);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(tempXpath3)));
			WebDriverAction.getDriver().findElement(By.xpath(tempXpath3)).click();
			//Thread.sleep(3000);
			int size = WebDriverAction.getDriver().findElements(By.xpath(tempXpath3)).size();
			System.out.println("size: "+size);
			Thread.sleep(1000);
			String tempXpath4 = "";
			tempXpath4 = "(//*[@id='countriddid']//*[@id='dropdown']//*[@id='contentWrapper']//*[@role='option' and text()='"+country+"'])";
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickCountry_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickCountry_XPATH);
			BrowserAction.click(LoginPageObject.ClickCountry_XPATH);
			System.out.println("tempXpath4: "+tempXpath4);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tempXpath4)));
			WebDriverAction.getDriver().findElement(By.xpath(tempXpath4)).click();
			//Thread.sleep(3000);
			
			//Thread.sleep(3000);
			
			
			String tempXpath5 = "//*[@id='profsiteid']//*[@id='dropdown']//paper-listbox[@role='listbox']//paper-item[text()='"+Location_Site+"']";
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tempXpath5)));
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickLocation_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickLocation_XPATH);
			BrowserAction.click(LoginPageObject.ClickLocation_XPATH);
			WebDriverAction.getDriver().findElement(By.xpath(tempXpath5)).click();;
			
			//Thread.sleep(3000);
			Thread.sleep(2000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.Save_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.Save_XPATH);
			BrowserAction.click(LoginPageObject.Save_XPATH);
			
			Thread.sleep(2000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ok_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ok_XPATH);
			//PDFReporter.takeExtraScreenshot();
			BrowserAction.click(LoginPageObject.ok_XPATH);
			
			String searchResultXpath ="(//*[@id='dataTable']//*[@role='row'])";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchResultXpath)));
			
	}
	
	@Test(priority = 4,enabled=false)
	@Documentation(step = "Click on Save.", expected = "Request successfully Submitted .")
	@Actual(actual = "User is Successfully able to create new change Request.")
	@FailureMessage("User is not able to see page due to some issue.")
	public void SaveandSubmit() throws Exception
	{
			/*Thread.sleep(5000);
			WebElement element = driver.findElement(By.xpath("//div/section[@class='style-scope profile-view']/following :: button[@id='nextBtnRequestId']"));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().build().perform();*/
			
			
			Thread.sleep(5000);
			WebElement element1 = driver.findElement(By.xpath("//paper-dialog[@id='modalsuccess']//div/button"));
			Actions action = new Actions(driver);
			action.moveToElement(element1).click().build().perform();
			/*BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ok_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ok_XPATH);
			//PDFReporter.takeExtraScreenshot();
			BrowserAction.click(LoginPageObject.ok_XPATH);*/
			
	}	
		

	
		
	@Test(priority = 5,dataProvider="ChangeRequest")
	@Documentation(step = "Click on Create Change then Save and Next.", expected = "Should move to the next page if data filled is Correct .")
	@Actual(actual = "User is Successfully able to create new change Request.")
	@FailureMessage("User is not able to see page due to some issue.")
	public void clickToCreaterequest(String projectid,String projectname,String projectowner,String projectlocation,String projectstartdate,
	String projectcompletiondate,String projecttimeline,String projectteam,String currentsituation,String projectedsituation,
	String dataretentionperiod,String ChangeDomain,String Changetype, String Priority,String Reviewer,String EnterReviewer ) throws Exception {
		try {
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.Create_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.Create_XPATH);
			BrowserAction.click(LoginPageObject.Create_XPATH);
			Thread.sleep(1000);	
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.Projectid_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.Projectid_XPATH);
			BrowserAction.getElement(LoginPageObject.Projectid_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.Projectid_XPATH).sendKeys(projectid);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectName_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectName_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectName_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectName_XPATH).sendKeys(projectname);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectOwner_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectOwner_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectOwner_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectOwner_XPATH).sendKeys(projectowner);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectLocation_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectLocation_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectLocation_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectLocation_XPATH).sendKeys(projectlocation);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectSTARTdate_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectSTARTdate_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectSTARTdate_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectSTARTdate_XPATH).sendKeys(projectstartdate);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectENDdate_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectENDdate_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectENDdate_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectENDdate_XPATH).sendKeys(projectcompletiondate);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectTimeline_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectTimeline_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectTimeline_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectTimeline_XPATH).sendKeys(projecttimeline);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectTeam_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectTeam_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectTeam_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectTeam_XPATH).sendKeys(projectteam);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectCurrSituation_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectCurrSituation_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectCurrSituation_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectCurrSituation_XPATH).sendKeys(currentsituation);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectProjSituation_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectProjSituation_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectProjSituation_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectProjSituation_XPATH).sendKeys(projectedsituation);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectDataRetenPeriod_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectDataRetenPeriod_XPATH);
			BrowserAction.getElement(LoginPageObject.ProjectDataRetenPeriod_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.ProjectDataRetenPeriod_XPATH).sendKeys(dataretentionperiod);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectSave_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectSave_XPATH);
			//BrowserAction.getElement(LoginPageObject.ProjectSave_XPATH).clear();
			BrowserAction.click(LoginPageObject.ProjectSave_XPATH);
			
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LoginPageObject.ProjectNext_XPATH.toString())));
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ProjectNext_XPATH);
			BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ProjectNext_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ProjectNext_XPATH);
			//BrowserAction.getElement(LoginPageObject.ProjectNext_XPATH).clear();
			BrowserAction.click(LoginPageObject.ProjectNext_XPATH);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.Clickdropdown_XPATH);
			BrowserWait.waitUntilElementIsEnabled(LoginPageObject.Clickdropdown_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.Clickdropdown_XPATH);
			BrowserAction.click(LoginPageObject.Clickdropdown_XPATH);
			
			Thread.sleep(1000);
			String DomianXpath = "//ul[@id='list']/li[normalize-space(text())='"+ChangeDomain+"']";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(DomianXpath)));
			WebDriverAction.getDriver().findElement(By.xpath(DomianXpath)).click();
			wait.until(ExpectedConditions.attributeContains(By.id("loading"), "style", "display: block;"));
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ChangeTypedropdown_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ChangeTypedropdown_XPATH);
			BrowserAction.click(LoginPageObject.ChangeTypedropdown_XPATH);
			Thread.sleep(1000);
			String TypeXpath = "//ul[@id='list']/li[normalize-space(text())='"+Changetype+"']";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TypeXpath)));
			WebDriverAction.getDriver().findElement(By.xpath(TypeXpath)).click();
			wait.until(ExpectedConditions.attributeContains(By.id("loading"), "style", "display: block;"));
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.PriorityDropdown_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.PriorityDropdown_XPATH);
			BrowserAction.click(LoginPageObject.PriorityDropdown_XPATH);
			Thread.sleep(1000);
			String PriorXpath = "//ul[@id='list']/li[normalize-space(text())='"+Priority+"']";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(PriorXpath)));
			WebDriverAction.getDriver().findElement(By.xpath(PriorXpath)).click();		
			Thread.sleep(1000);	
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ChooseYes_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ChooseYes_XPATH);
			BrowserAction.click(LoginPageObject.ChooseYes_XPATH);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickAddReviewer_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickAddReviewer_XPATH);
			BrowserAction.click(LoginPageObject.ClickAddReviewer_XPATH);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickReviewerdropdown_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickReviewerdropdown_XPATH);
			BrowserAction.click(LoginPageObject.ClickReviewerdropdown_XPATH);
			
			Thread.sleep(1000);
			String ReviewXpath = "//ul[@id='list']/li[normalize-space(text())='"+Reviewer+"']";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ReviewXpath)));
			WebDriverAction.getDriver().findElement(By.xpath(ReviewXpath)).click();
			
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.SearchReviewer_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.SearchReviewer_XPATH);
			BrowserAction.click(LoginPageObject.SearchReviewer_XPATH);
			BrowserAction.getElement(LoginPageObject.SearchReviewer_XPATH).clear();
			BrowserAction.getElement(LoginPageObject.SearchReviewer_XPATH).sendKeys(EnterReviewer);
			
			
			Thread.sleep(1000);
			String EnterReviewerXpath = "//div/ul[@id='searchResults']/li/span[normalize-space(text())='"+EnterReviewer+"']";
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(EnterReviewerXpath)));
			WebDriverAction.getDriver().findElement(By.xpath(EnterReviewerXpath)).click();
			
			Actions actions = new Actions(driver);
			WebElement elementLocator = driver.findElement(By.xpath("//div/input[@id='search' and @placeholder='Select Reviewer']"));
			actions.doubleClick(elementLocator).perform();
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickonADD_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickonADD_XPATH);
			BrowserAction.click(LoginPageObject.ClickonADD_XPATH);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickSave_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickSave_XPATH);
			BrowserAction.click(LoginPageObject.ClickSave_XPATH);
			
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.SubmitRequest_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.SubmitRequest_XPATH);
			BrowserAction.click(LoginPageObject.SubmitRequest_XPATH);
			Thread.sleep(1000);
			while(!BrowserAction.getElement(LoginPageObject.SubmitButton_XPATH).isEnabled())
			{
				
				int Count= driver.findElements(By.xpath("//div[@class='div-tab-col div-width-s style-scope moc-ques']//div[@id='radioContainer']//div[@id='offRadio']")).size();
				System.out.println("Count: "+Count);
				for(int i = 1; i<=Count; i++)
				{
					String xpath="(//div[@class='div-tab-col div-width-s style-scope moc-ques']//div[@id='radioContainer']//div[@id='offRadio'])["+i+"]";
					try {
						if(WebDriverAction.getDriver().findElement(By.xpath(xpath)).isDisplayed() && WebDriverAction.getDriver().findElement(By.xpath(xpath)).isEnabled())
						{
							WebDriverAction.getDriver().findElement(By.xpath(xpath)).click();
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
			
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.SubmitButton_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.SubmitButton_XPATH);
			BrowserAction.click(LoginPageObject.SubmitButton_XPATH);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.Popup_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.Popup_XPATH);
			BrowserAction.click(LoginPageObject.Popup_XPATH);
			
			Thread.sleep(1000);
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.AfterSubmitOk_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.AfterSubmitOk_XPATH);
			BrowserAction.click(LoginPageObject.AfterSubmitOk_XPATH);
			
			String searchResultXpath ="(//*[@id='dataTable']//*[@role='row'])";
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchResultXpath)));
			//Thread.sleep(1000);
			
			/*BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.NextProfile_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.NextProfile_XPATH);
			BrowserAction.click(LoginPageObject.NextProfile_XPATH);
			
			BrowserWait.waitUntilElementIsDisplayed(LoginPageObject.ClickBusiness_XPATH);
			BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickBusiness_XPATH);*/

		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println(projectid +" >> "+projectname+" >> "+ projectowner+" >> "+ projectlocation+" >> "+ projectstartdate+projectcompletiondate+" >> "+ projecttimeline+" >> "+ projectteam+" >> "+ currentsituation+" >> "+ projectedsituation+
					dataretentionperiod+" >> "+ ChangeDomain+" >> "+ Changetype+">>"+Priority+" >> "+ Reviewer+" >> "+ EnterReviewer);
		}
		
		
	}
	
	@Test(priority = 6)
	@Documentation(step = "Navigate to Change Request.", expected = "Should fill the details and click on Submit .")
	@Actual(actual = "User is able to verify the data which has been created")
	@FailureMessage("User is not able to see page due to some issue.")
	public void Movetocreate() throws Exception
	{
		String myRequestXpa="//a[@id='#/myChangeRequests']";
		WebDriverAction.getDriver().findElement(By.xpath(myRequestXpa)).click();
		
		String searchResultXpath ="(//*[@id='dataTable']//*[@role='row'])";
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchResultXpath)));
	}
	
	
	@Test(priority = 7,dataProvider="ChangeRequest")
	@Documentation(step = "Fill the Data Accordingly then Click on Save and Submit.", expected = "Should fill the details and click on Submit .")
	@Actual(actual = "User is able to see the Profile page.")
	@FailureMessage("User is not able to see page due to some issue.")
	public void AnotherProfile(String projectid,String projectname,String projectowner,String projectlocation,String projectstartdate,
			String projectcompletiondate,String projecttimeline,String projectteam,String currentsituation,String projectedsituation,
			String dataretentionperiod,String ChangeDomain,String Changetype, String Priority,String Reviewer,String EnterReviewer ) throws Exception
	{
		
		String searchResultXpath ="(//*[@id='dataTable']//*[@role='row'])";
		
		String projectNameXpath="((//*[@id='dataTable']//*[@role='row'])[2]//input)[2]";
		WebDriverAction.getDriver().findElement(By.xpath(projectNameXpath)).clear();
		WebDriverAction.getDriver().findElement(By.xpath(projectNameXpath)).sendKeys(projectname);
		Thread.sleep(2000);
		PDFReporter.takeExtraScreenshot();
		int resultSize = WebDriverAction.getDriver().findElements(By.xpath(searchResultXpath)).size();
		
		if(resultSize>3)
		{
			for(int i=3;i<=resultSize;i++)
			{
				String projectNameTempXpath=searchResultXpath+"["+i+"]//px-data-table-cell[2]//*[@id='cellvalue']";
				String TempProjectName = WebDriverAction.getDriver().findElement(By.xpath(projectNameTempXpath)).getText().trim();
				String statusTempXpath=searchResultXpath+"["+i+"]//px-data-table-cell[8]//*[@id='cellvalue']";
				String statusTemp = WebDriverAction.getDriver().findElement(By.xpath(statusTempXpath)).getText().trim();
				if(TempProjectName.equalsIgnoreCase(projectname) && statusTemp.equalsIgnoreCase("Submitted"))
				{
					System.out.println("TempProjectName: "+TempProjectName+" Verified");
				}
				
			}
		}
	}
		
	
	
	@DataProvider(name="ChangeRequest")
    public Object[][] getDataFromDataprovider() throws Exception{
		
		//String path;
		//String name;
		Object[][] data =  ReadExcel.readExcelDatatoMap("C:\\Users\\saupraja\\Documents\\My Received Files\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\main\\java\\com\\Excelfile", "Book1.xlsx","Sheet1",16);
        return data;
    }
	
	@DataProvider(name="BusinessData")
	public Object[][] getBusinessData() throws Exception{
		
		//String path;
		//String name;
		Object[][] data =  ReadExcel.readExcelDatatoMap("C:\\Users\\saupraja\\Documents\\My Received Files\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\main\\java\\com\\Excelfile", "Book1.xlsx","Sheet2",6);
	    return data;
	}

}
