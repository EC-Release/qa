package com.ge.corp.GePower.Pages.test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
	import org.apache.poi.xssf.usermodel.XSSFCell;
	import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import bsh.org.objectweb.asm.Constants;

import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.CellType;
	public class ExcelUtility {
	    private static XSSFSheet ExcelWSheet;
	    private static XSSFWorkbook ExcelWBook;
	    private static XSSFCell cell;
	    private static XSSFRow row;
	    //This method is to set the File path and to open the Excel 
	   // file, Pass Excel Path and Sheetname as Arguments to this method
	    public static void setExcelFile(String Path, String SheetName) throws Exception {
	        try {
	            // Open the Excel file
	            FileInputStream ExcelFile = new FileInputStream("C:\\Users\\saupraja\\Documents\\My Received Files\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\main\\java\\com\\Excelfile\\Book1.xlsx");
	            // Access the required test data sheet
	            ExcelWBook = new XSSFWorkbook(ExcelFile);
	            ExcelWSheet = ExcelWBook.getSheetAt(0);
	        } catch (Exception e) {
	            throw (e);
	        }
	    }
	    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	    public static String getCellData(int RowNum, int ColNum) throws Exception {
	        try {
	            String cellData = "";
	            cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	            cell.setCellType(CellType.STRING);
	            cellData = cell.getStringCellValue();
	            return cellData;
	        } catch (Exception e) {
	            return "undefined";
	        }
	    }
	    
	   // String Projectid = ExcelFile.getCellData()
	    //This method is to write in the Excel cell, Row num and Col num are the parameters
	   /* public static void setCellData(String Result, int RowNum, int ColNum) throws Exception {
	        try {
	            row = ExcelWSheet.getRow(RowNum);
	            cell = row.getCell(ColNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
	            if (cell == null) {
	                cell = row.createCell(ColNum);
	                cell.setCellValue(Result);
	            } else {
	                cell.setCellValue(Result);
	            }
	            // Constant variables Test Data path and Test Data file name
	            FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData + Constants.File_TestData);
	            ExcelWBook.write(fileOut);
	            fileOut.flush();
	            fileOut.close();
	        } catch (Exception e) {
	            throw (e);
	        }
	    }*/
	}


