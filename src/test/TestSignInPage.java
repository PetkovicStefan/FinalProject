package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
	public void setup() throws FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.locators =  new Properties();
		locators.load(new FileInputStream("config/locators.properties"));
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.waiter = new WebDriverWait(driver, 30);
		this.driver.navigate().to(this.locators.getProperty("urlMenuPetStore"));
	}
	
	@Test
	public void isSignedIn() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		menuPage.enterSignInPage();
		
		SignInPage signInPage = new SignInPage(driver, locators, waiter);
		
		SoftAssert sa = new SoftAssert();
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		
		for(int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String username = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			
			signInPage.signIn(username, password);
			sa.assertTrue(signInPage.signedIn());
			signInPage.signOut();
			menuPage.enterSignInPage();
		}
		sa.assertAll();
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
