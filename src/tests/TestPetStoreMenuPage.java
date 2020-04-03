package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
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

public class TestPetStoreMenuPage {
	
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
		this.driver.navigate().to(this.locators.getProperty("urlMenuPetStore"));
	}
	
	@Test (priority = 1)
	public void isUrlAdressWorking() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(menuPage.isUrlMenuWorks(menuPage.getLeftNavMenu()));
		sa.assertTrue(menuPage.isUrlMenuWorks(menuPage.getCenterNavMenu()));
		sa.assertTrue(menuPage.isUrlMenuWorks(menuPage.getImageNavMenu()));
		sa.assertAll();
	}
	
	@Test (priority = 2)
	public void isLoadedPageCorrect() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(menuPage.leftNavMenuCorrectPage());
		sa.assertTrue(menuPage.centerNavMenuCorrectPage());
		sa.assertTrue(menuPage.imageNavMenuCorrectPage());
	}
	
	@Test (priority = 3)
	public void isSignInButtonWorks() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		menuPage.enterSignInPage();
		Assert.assertTrue(menuPage.isEnteredSignInPage());
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
