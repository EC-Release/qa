package com.ge.corp.DigitalConnect.ec_automationpoc.scripts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.ge.corp.DigitalConnect.ec.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	String directoryPath = "C:\\users\\sareddyc\\SampleEC\\";
	String uaa_secret_path = "C:\\users\\sareddyc\\SampleEC\\uaa-secret.json";
	String uaa_trustedIssuerId_path = "C:\\users\\sareddyc\\SampleEC\\uaa-issuerid.json";
	String VCAP_path = "C:\\users\\sareddyc\\SampleEC\\VCAP.json";
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("C:\\Users\\sareddyc\\Documents\\DC Stuff\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\test\\java\\com\\ge\\corp\\DigitalConnect\\ec\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//BaseTest_EnterpriseConnect ec = new BaseTest_EnterpriseConnect();
	
	public void initialization(String url) throws IOException, JSONException, InterruptedException {
		String browserName = prop.getProperty("browser");
		System.out.println("Identified the browser name as : " + browserName);
		
		if(browserName.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sareddyc\\Documents\\DC Stuff\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();			
		}else if(browserName.equals("FIREFOX")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\sareddyc\\Documents\\DC Stuff\\EHS-Management_Of_Change\\EHS-Management_Of_Change\\src\\test\\resources\\drivers\\chromedriver.exe");
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		//driver.get(JsonTesting.getKeyValueFromUaa_Secret(uaa_secret_path, "dashboardUrl"));
		driver.get(url);
		Thread.sleep(3000);
	}
	
	

}
