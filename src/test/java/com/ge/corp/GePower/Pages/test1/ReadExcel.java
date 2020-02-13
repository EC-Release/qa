package com.ge.corp.GePower.Pages.test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ge.corp.GePower.Objects.test1.LoginPageObject;
import com.ge.digital.itops.browser.BrowserAction;
import com.ge.digital.itops.webdriver.BaseWebDriver;

public class ReadExcel extends BaseWebDriver {
	// //static Cell Data1;
	// //static Cell Data2;
	/*static String projectid;
	static String projectname;
	static String projectowner;
	static String projectlocation;
	static String projectstartdate;
	static String projectcompletiondate;
	static String projecttimeline;
	static String projectteam;
	static String currentsituation;
	static String projectedsituation;
	static String dataretentionperiod;
	
	
	  public static void setExcelFile() throws Exception {
	//public static void main(String[] args) throws Exception {
		// I have placed an excel file 'Test.xlsx' in my D Driver
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\bijaiswa\\Documents\\Bineet Workspace\\EHS-Management_Of_Change\\src\\main\\java\\com\\Excelfile\\Book1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		// I have added test data in the cell A1 as "SoftwareTestingMaterial.com"
		// Cell A1 = row 0 and column 0. It reads first row as 0 and Column A as 0.
		// Row row = sheet.getRow(2);
		// Cell cell = row.getCell(0);

		
		DataFormatter formatter = new DataFormatter();
		
		int rowcount = sheet.getLastRowNum();
		for(int i = 1;i<=rowcount;i++)
		{
			projectname = formatter.formatCellValue(sheet.getRow(1).getCell(1));
			projectowner = formatter.formatCellValue(sheet.getRow(1).getCell(2));
			projectlocation = formatter.formatCellValue(sheet.getRow(1).getCell(3));
			projectid = formatter.formatCellValue(sheet.getRow(1).getCell(0));
			projectstartdate = formatter.formatCellValue(sheet.getRow(1).getCell(4));
			projectcompletiondate = formatter.formatCellValue(sheet.getRow(1).getCell(5));
			projecttimeline = formatter.formatCellValue(sheet.getRow(1).getCell(6));
			projectteam = formatter.formatCellValue(sheet.getRow(1).getCell(7));
			currentsituation = formatter.formatCellValue(sheet.getRow(1).getCell(8));
			projectedsituation = formatter.formatCellValue(sheet.getRow(1).getCell(9));
			dataretentionperiod = formatter.formatCellValue(sheet.getRow(1).getCell(10));
		}
		
		
		//String val = formatter.formatCellValue(sheet.getRow(row).getCell(col));
		//projectid.setCellType(Cell.CELL_TYPE_STRING);
		System.out.println(projectid);
		//new ReadExcel().ProjectID(projectid);
		

	//}
	  }

	    public void ProjectID() throws Exception {
		Thread.sleep(1000);
		BrowserAction.getElement(LoginPageObject.Projectid_XPATH).sendKeys(projectid);
	}

	    public void ProjectNAME() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectName_XPATH).sendKeys(projectname);
		}
	    
	    public void ProjectOWNER() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectOwner_XPATH).sendKeys(projectowner);
		}
	    
	    public void ProjectLOCATION() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectLocation_XPATH).sendKeys(projectlocation);
		}
	    
	    public void ProjectSTARTDATE() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectSTARTdate_XPATH).sendKeys(projectstartdate);
		}
	    
	    public void ProjectCOMPLETIONDATE() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectENDdate_XPATH).sendKeys(projectcompletiondate);
		}
	    
	    public void ProjectTIMELINE() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectTimeline_XPATH).sendKeys(projecttimeline);
		}
	    
	    public void ProjectTEAM() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectTeam_XPATH).sendKeys(projectteam);
		}
		
	 	public void CURRENTSITUATION() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectCurrSituation_XPATH).sendKeys(currentsituation);
		}
		
		 public void PROJECTEDSITUATION() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectProjSituation_XPATH).sendKeys(projectedsituation);
		}
		
		 public void DataRetentionPeriod() throws Exception {
			Thread.sleep(1000);
			BrowserAction.getElement(LoginPageObject.ProjectDataRetenPeriod_XPATH).sendKeys(dataretentionperiod);
		}
		 
		 public void ProjectSAVE() throws Exception {
				Thread.sleep(1000);
				BrowserAction.click(LoginPageObject.ProjectSave_XPATH);
			}
		 
		 public void ProjectNEXT() throws Exception {
				Thread.sleep(1000);
				BrowserAction.click(LoginPageObject.ProjectNext_XPATH);
			}*/

	    
	    
	    
	//
	// // System.out.println(cell);
	// // System.out.println(sheet.getRow(0).getCell(0));
	// /*DataFormatter df = new DataFormatter();
	// cellval = df.formatCellValue(cell);
	// System.out.println(cellval);*/
	//
	// //String projectid = cell.getStringCellValue();
	// //System.out.println(cellval);
	// }
	//
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



public static Object[][] readExcelDatatoMap(String path, String filename,String sheetname, int totalCol) throws Exception
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
                 rowCount = sheet.getLastRowNum();
                 DataFormatter df = new DataFormatter();
                 System.out.println("rowCount :" +rowCount);
                 excelData = new Object[rowCount][totalCol];
                 //row = sheet.getRow(9);
                 for (int i = 1; i <=rowCount; i++) {
                       for (int j = 0; j <totalCol; j++) {
                              excelData[i-1][j] = df.formatCellValue(sheet.getRow(i).getCell(j)).trim();
                              System.out.print(" // "+ excelData[i-1][j]);
                       }
                       System.out.println();
                      
                 }
          } catch (Exception e) {
                 e.printStackTrace();
          }          
    return excelData;
}

}

/*
 * public static Object[][] readExcel(String filePath, String sheetName) throws
 * InvalidFormatException, IOException { FileInputStream file= new
 * FileInputStream(filePath); XSSFWorkbook wb = new XSSFWorkbook(file);
 * XSSFSheet sheet = wb.getSheet(sheetName); int rowCount =
 * sheet.getLastRowNum(); int column = sheet.getRow(0).getLastCellNum();
 * Object[][] data = new Object[rowCount][column]; for (int i = 1; i <=
 * rowCount; i++) { XSSFRow row = sheet.getRow(i); for (int j = 0; j < column;
 * j++) { XSSFCell cell = row.getCell(j); DataFormatter formatter = new
 * DataFormatter(); String val = formatter.formatCellValue(cell); data[i - 1][j]
 * = val; } }
 * 
 * return data; }
 * 
 * }
 */