package com.ge.corp.GePower.scripts.test1;

import java.io.File;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
//import jxl.Workbook;
import org.testng.annotations.Test;

import com.ge.corp.GePower.Objects.test1.LoginPageObject;
import com.ge.corp.GePower.Pages.test1.BaseTest;
//import com.ge.corp.GePower.Pages.test1.ChangeRequestpage;
import com.ge.corp.GePower.Pages.test1.LoginPage;
import com.ge.corp.GePower.Pages.test1.ReadExcel;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.testng.Documentation;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestScript extends BaseTest {
	//static LoginPage login;
	static ReadExcel read;
	//static ChangeRequest chr;
	//static FleetReport fr;
	//static SiteWeather Sw;
	//static SiteProduction pr;
	
	
/*	@Test(priority = 1)
	@Documentation(step = "Login to the application", expected = "User should be able to Login to the Application")
	public void login() throws Exception {

		login = new LoginPage();
		login.enterCredential("503115288", "VI1995neet");
		//siteHeader.selectSiteFromDropdown("Palaswade");
	}
*/
/*	@Test(priority = 2)
	public void clickLoginButton() throws Exception
	{
		login.clickLoginButton();
	}
	
	@Test(priority = 3)
	public void EnterProfile() throws Exception
	{
		login.EnterProfile();
	}*/
	
	
/*	@Test(priority = 4)
	public void SaveProfile() throws Exception
	{
		login.SaveProfile();
	}*/

	/*@Test(priority = 5)
	public void Saveok() throws Exception
	{
		login.Saveok();
	}
	
	@Test(priority = 6)
	public void CreateChange() throws Exception
	{
		login.CreateChange();
	}*/
	
	/*@Test(priority = 7)
	public void setExcelFile() throws Exception
	{
		read = new ReadExcel();
		read.setExcelFile();
	}
	


	@Test(priority = 8)
	public void ProjectID() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectID();
	}
	
	@Test(priority = 8)
	public void ProjectNAME() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectNAME();
	}
	
	@Test(priority = 9)
	public void ProjectOWNER() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectOWNER();
	}
	
	@Test(priority = 10)
	public void ProjectLOCATION() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectLOCATION();
	}
	
	@Test(priority = 11)
	public void ProjectSTARTDATE() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectSTARTDATE();
	}
	
	@Test(priority = 12)
	public void ProjectCOMPLETIONDATE() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectCOMPLETIONDATE();
	}
	
	@Test(priority = 13)
	public void ProjectTIMELINE() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectTIMELINE();
	}
	
	@Test(priority = 14)
	public void ProjectTEAM() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectTEAM();
	}
	
	@Test(priority = 15)
	public void CURRENTSITUATION() throws Exception
	{
		//read = new ReadExcel();
		read.CURRENTSITUATION();
	}
	
	@Test(priority = 16)
	public void PROJECTEDSITUATION() throws Exception
	{
		//read = new ReadExcel();
		read.PROJECTEDSITUATION();
	}
	
	@Test(priority = 17)
	public void DataRetentionPeriod() throws Exception
	{
		//read = new ReadExcel();
		read.DataRetentionPeriod();
	}
	
	@Test(priority = 18)
	public void ProjectSAVE() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectSAVE();
	}
	
	@Test(priority = 19)
	public void ProjectNEXT() throws Exception
	{
		//read = new ReadExcel();
		read.ProjectNEXT();
	}*/
	
	
	
	/* d Excel FileJava

package softwareTestingMaterial;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

//How to read excel files using Apache POI
/*public class ReadExcel {
	public static void main (String [] args) throws IOException{
                        //I have placed an excel file 'Test.xlsx' in my D Driver 
			FileInputStream fis = new FileInputStream("D:\\Test.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
                        //I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
                        //Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
                       	System.out.println(cell);
			System.out.println(sheet.getRow(0).getCell(0));
			//String cellval = cell.getStringCellValue();
			//System.out.println(cellval);
			
	}		
}
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
package softwareTestingMaterial;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
//How to read excel files using Apache POI
public class ReadExcel {
 public static void main (String [] args) throws IOException{
                        //I have placed an excel file 'Test.xlsx' in my D Driver 
 FileInputStream fis = new FileInputStream("D:\\Test.xlsx");
 XSSFWorkbook workbook = new XSSFWorkbook(fis);
 XSSFSheet sheet = workbook.getSheetAt(0);
                        //I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
                        //Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
 Row row = sheet.getRow(0);
 Cell cell = row.getCell(0);
                       System.out.println(cell);
 System.out.println(sheet.getRow(0).getCell(0));
 //String cellval = cell.getStringCellValue();
 //System.out.println(cellval);
 
 } 
}
	 */

	
	/*
	 Workbook wBook = Workbook.getWorkbook(new File("E:\\Testdata\\ShellData.xls"));
//get sheet
jxl.Sheet Sheet = wBook.getSheet(0); 
//Now in application i have given my Username and Password input in following way
driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys(Sheet.getCell(0, i).getContents());
driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(Sheet.getCell(1, i).getContents());
driver.findElement(By.xpath("//input[@name='Login']")).click();
	 */

	
	//SaveProfile
	
   /* @Test(priority = 3)
	
	public void header() throws Exception
	{
		fr = new FleetReport();
		fr.header();
		//WebElement TxtBoxContent = driver.findElement(By.xpath("//main[@class=\"sapm-common-container ng-scope\"]//span[text()='FLEET OVERVIEW']"));
		//TxtBoxContent.getText();
	}
    
	
	@Test(priority = 4)
	
	public void clicksite() throws Exception
	{
		fr = new FleetReport();
		fr.clicksite();
	}

	@Test(priority = 5)
	public void weatherlink() throws Exception
	{
		Sw = new SiteWeather();
		Sw.weatherlink();
	}
	
	@Test(priority = 6)
	public void siteselection() throws Exception
	{
		Sw = new SiteWeather();
		Sw.siteselection();
	}
	
	@Test(priority = 7)
	public void siteheader() throws Exception
	{
		Sw = new SiteWeather();
		Sw.siteheader();
	}
	
	@Test(priority = 8)
	public void productionlink() throws Exception
	{
		pr = new SiteProduction();
		pr.productionlink();
	}
	
	@Test(priority = 9)
	public void clickdownload() throws Exception
	{
		pr = new SiteProduction();
		pr.clickdownload();
	}
	
	@Test(priority = 10)
	public void downloadcsv() throws Exception
	{
		pr = new SiteProduction();
		pr.downloadcsv();
	}
	*/
	
	
}
