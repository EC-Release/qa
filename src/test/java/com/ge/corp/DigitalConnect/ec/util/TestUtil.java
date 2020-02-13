package com.ge.corp.DigitalConnect.ec.util;

import org.openqa.selenium.WebElement;

public class TestUtil {
	public static long PAGE_LOAD_TIMEOUT = 60;
	public static long IMPLICIT_WAIT = 10;
	public static boolean flag;

	public static void findElementTextbox(WebElement element, String fieldValue, String fieldName) {
		try {
			if (element.isDisplayed()) {
				if (element.isEnabled()) {
					try {
						element.click();
						element.clear();
						element.sendKeys(fieldValue);
						flag = true;
						// System.out.println("PASS, " + fieldValue + " Entered on " + fieldName + "
						// Text Field");
					} catch (Exception e) {
						flag = false;
						// System.err.println("Unable to locate element '" + fieldName + "'");
						e.printStackTrace();
						throw (e);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception thrown in findElementTextbox Method---------> " + e);
			// System.err.println("Unable to check display status of element '" + fieldName
			// + "'");
			flag = false;
			e.printStackTrace();
			throw (e);
		}
		if (flag == false) {
			System.out.println("FAIL, " + fieldValue + " Not Entered on " + fieldName + " as " + fieldName
					+ " Text Field Object is Not Present");
		}
	}
	
	public static void findElementToClick(WebElement element, String fieldName) {
        try {
               if (element.isDisplayed()) {
                     if (element.isEnabled()) {
                            try {
                                   element.click();
                                   flag = true;
                                   // System.out.println("PASS, Clicked on " + fieldName + " Button/Link Field");
                            } catch (Exception e) {
                                   flag = false;
                                   e.printStackTrace();
                                   throw (e);
                            }
                     }
               }
        } catch (Exception e) {
               System.out.println("Exception thrown in findElementToClick Method---------> " + e);
               flag = false;
               e.printStackTrace();
               throw (e);
        }
        if (flag == false) {
               System.out.println("FAIL, Not Clicked on " + fieldName + " as " + fieldName
                            + " Button/Link/Checkbox Field Object is Not Present");
        }
 }


}
