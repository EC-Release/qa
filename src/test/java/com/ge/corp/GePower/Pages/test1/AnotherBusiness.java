package com.ge.corp.GePower.Pages.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ge.corp.GePower.Objects.test1.LoginPageObject;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.browser.BrowserAssert;
import com.ge.digital.itops.browser.BrowserWait;
import com.ge.digital.itops.webdriver.BaseWebDriver;

public class AnotherBusiness extends BaseWebDriver {
	
	 public static Workbook getWorkbook(String path, String name) throws Exception {
         Workbook wb = null;
         File file = new File(path + "\\" + name);
         FileInputStream inputStream = new FileInputStream(file);
         String fileExtensionName = name.substring(name.indexOf("."));
         try {
                if (fileExtensionName.equals(".xlsx")) {
                      wb = new XSSFWorkbook(inputStream);
                } else if (fileExtensionName.equals(".xls")) {
                      POIFSFileSystem fs = new POIFSFileSystem(inputStream);
                      wb = new HSSFWorkbook(fs);
                }

         } catch (Exception e) {
                e.printStackTrace();
         } finally {
                if (wb != null) {
                      //wb.close();
                }
                if (inputStream != null) {
                      inputStream.close();
                }
         }
         return wb;
  }

public static Sheet getsheetName(String path, String name, int index)
                throws IOException {
         Workbook wb = null;
         Sheet sheet = null;
         File file = new File(path + "\\" + name);
         FileInputStream inputStream = new FileInputStream(file);
         String fileExtensionName = name.substring(name.indexOf("."));
         try {
                if (fileExtensionName.equals(".xlsx")) {
                      wb = new XSSFWorkbook(inputStream);
                } else if (fileExtensionName.equals(".xls")) {
                      wb = new HSSFWorkbook(inputStream);
                }

                sheet = wb.getSheetAt(index);
         } catch (Exception e) {
                e.printStackTrace();
         } finally {
                if (wb != null) {
                      //wb.close();
                }
                if (inputStream != null) {
                      inputStream.close();
                }
         }

         return sheet;

  }



public static Object[][] readExcelDatatoMap(String path, String filename,String sheetname) throws Exception
{
      Sheet sheet = null;
      int rowCount = 0;
      int index=-1;
Object[][] excelData = null;
try {
			sheet = getWorkbook(path, filename).getSheet(sheetname);
             //rowCount = sheet.getLastRowNum();
             //System.out.println("Row Count :- " + rowCount);
             //XSSFRow row = null;
             //int colCount = sheet.getLastCellNum();
             //System.out.println("Column Count :- " + colCount);
             rowCount = sheet.getLastRowNum() + 1;
             DataFormatter df = new DataFormatter();
             System.out.println("rowCount :" +rowCount);
             excelData = new Object[rowCount][6];
             //row = sheet.getRow(9);
             for (int i = 1; i <rowCount; i++) {
                   for (int j = 0; j <6; j++) {
                          excelData[i-1][j] = df.formatCellValue(sheet.getRow(i).getCell(j));
                          
                   }
             }
      } catch (Exception e) {
             e.printStackTrace();
      }          
return excelData;
}


@Test(dataProvider = "otherRequest") 
public static void AnotherProfile(String Business,String organization,String sub_organization,String region,String country,String Location_Site ) throws Exception
{
	Thread.sleep(2000);
	BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ClickBusiness_XPATH);
	BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickBusiness_XPATH);
	BrowserAction.click(LoginPageObject.ClickBusiness_XPATH);
	
	Thread.sleep(2000);
	BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ClickOrganisation_XPATH);
	BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickOrganisation_XPATH);
	BrowserAction.click(LoginPageObject.ClickOrganisation_XPATH);
	
	Thread.sleep(2000);
	BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ClickSubOrganisation_XPATH);
	BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickSubOrganisation_XPATH);
	BrowserAction.click(LoginPageObject.ClickSubOrganisation_XPATH);
	
	Thread.sleep(2000);
	BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ClickRegion_XPATH);
	BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickRegion_XPATH);
	BrowserAction.click(LoginPageObject.ClickRegion_XPATH);
	
	Thread.sleep(2000);
	BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ClickCountry_XPATH);
	BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickCountry_XPATH);
	BrowserAction.click(LoginPageObject.ClickCountry_XPATH);
	
	Thread.sleep(2000);
	BrowserWait.waitUntilElementIsEnabled(LoginPageObject.ClickLocation_XPATH);
	BrowserAssert.assertElementIsDisplayed(LoginPageObject.ClickLocation_XPATH);
	BrowserAction.click(LoginPageObject.ClickLocation_XPATH);
	
}



@DataProvider(name="otherRequest")
public Object[][] getDataFromDataprovider() throws Exception{
	
	//String path;
	//String name;
	Object[][] data =  ReadExcel.readExcelDatatoMap("C:\\Users\\saupraja\\Documents\\My Received Files\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\main\\java\\com\\Excelfile", "Book1.xlsx","Sheet2",6);
    return data;
}

}
