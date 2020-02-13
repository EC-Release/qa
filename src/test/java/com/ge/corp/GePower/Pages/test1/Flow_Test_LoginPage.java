package com.ge.corp.GePower.Pages.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.analysis.function.Add;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import com.ge.corp.GePower.Objects.test1.Flow_Test_Objects;
import com.ge.corp.GePower.scripts.test1.FlowTest;
import com.ge.digital.itops.browser.BrowserAccess;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.browser.BrowserAssert;
import com.ge.digital.itops.browser.BrowserWait;
import com.ge.digital.itops.webdriver.BaseWebDriver;
import com.ge.digital.itops.webdriver.WebDriverAccess;
import com.ge.digital.itops.webdriver.WebDriverAction;
import com.ge.digital.itops.webdriver.WebDriverWaits;

public class Flow_Test_LoginPage extends BaseWebDriver {	
	
	public static WebDriverWait wait=null;
		
	public static void enterCredentials(String username,String password) throws Exception
	{
		BrowserWait.waitUntilElementIsDisplayed(Flow_Test_Objects.username_ID);
		BrowserAction.getElement(Flow_Test_Objects.username_ID).clear();
		BrowserAction.getElement(Flow_Test_Objects.username_ID).sendKeys(username);
		BrowserAction.getElement(Flow_Test_Objects.password_ID).clear();
		BrowserAction.getElement(Flow_Test_Objects.password_ID).sendKeys(password);
		BrowserAction.getElement(Flow_Test_Objects.login_btn_ID).click();		
	}
	
	public static void sorting() throws Exception
	{
//		List<WebElement> col_length=WebDriverAccess.getElements(By.xpath("//div[contains(@class,'body') and contains(@class,'grid')]//td[contains(@class,'ACCOUNT_PHONE1')]"));
//		ArrayList<String> old_data=new ArrayList<String>();
//		int col_len=col_length.size();
//		for(int i=0;i<=col_len-1;i++) 
//		{
//			String old_value=col_length.get(i).getText();
//			old_data.add(old_value);
//		}		
//		System.out.print(old_data);
//		ArrayList<String> sorted_list=new ArrayList<String>();
//		Collections.sort(old_data,Collections.reverseOrder());
//		sorted_list.addAll(old_data);
//		System.out.print(old_data);
		String[] strarray=new String[] {"Test","Test1"};		
		for(int i=0;i<=strarray.length;i++)
		{
		
				System.out.println(strarray[i]);							
		}
	}
}
