package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class TestSignInPage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws Exception {
		if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "driver-lib\\firefoxdriver.exe");
			this.driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
			this.driver = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "driver-lib\\msedgedriver.exe");
			this.driver = new EdgeDriver();
		}
		else {
			throw new Exception("Browser is not correct");
		}
		this.locators =  new Properties();
		locators.load(new FileInputStream("config/locators.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.waiter = new WebDriverWait(driver, 30);
	}
	
	@Test
	public void isSignedIn() {
		SignInPage signInPage = new SignInPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell(locators.getProperty("petStoreExcelFile"));
		ExcelUtils.setWorkSheet(1);
		for(int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			this.driver.navigate().to(locators.getProperty("urlSignInPage"));
			signInPage.signIn(username, password);
			sa.assertTrue(signInPage.isSignedIn());
			signInPage.signOut();
		}
		sa.assertAll();
	}
	
	@AfterClass
	public void afterClass() {
		ExcelUtils.closeExcell();
		this.driver.close();
	}
}
