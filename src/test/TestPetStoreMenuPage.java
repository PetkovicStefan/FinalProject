package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;

public class TestPetStoreMenuPage {
	
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
	public void isUrlAdressWorking() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(menuPage.urlLeftNavMenuWorks());
		sa.assertTrue(menuPage.urlCenterNavMenuWorks());
		sa.assertTrue(menuPage.urlImageNavMenuWorks());
		sa.assertAll();
	}
	
	@Test
	public void isLoadedPageCorrect() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(menuPage.leftNavMenuCorrectPage());
		sa.assertTrue(menuPage.centerNavMenuCorrectPage());
		sa.assertTrue(menuPage.imageNavMenuCorrectPage());
		sa.assertAll();
	}
	
	@Test
	public void isSignInButtonWorks() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		Assert.assertTrue(menuPage.isEnteredSignInPage());
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
