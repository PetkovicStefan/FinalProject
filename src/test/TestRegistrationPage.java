package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.PetStoreMenuPage;
import pages.RegistrationPage;
import pages.SignInPage;
import utils.ExcelUtils;

public class TestRegistrationPage {
	
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
	public void isRegister() {
		PetStoreMenuPage menuPage = new PetStoreMenuPage(driver, locators, waiter);
		menuPage.enterSignInPage();
		SignInPage signIn = new SignInPage(driver, locators, waiter);
		signIn.clickRegisterNow();
		RegistrationPage regPage = new RegistrationPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(1);
		ExcelUtils.setUniqueId();
		for(int i = 1; i < ExcelUtils.getRowNumber(); i++) {
			String id = ExcelUtils.getDataAt(i, 0);
			String password = ExcelUtils.getDataAt(i, 1);
			String firstName = ExcelUtils.getDataAt(i, 2);
			String lastName = ExcelUtils.getDataAt(i, 3);
			String mail = ExcelUtils.getDataAt(i, 4);
			String phone = ExcelUtils.getDataAt(i, 5);
			String address1 = ExcelUtils.getDataAt(i, 6);
			String address2 = ExcelUtils.getDataAt(i, 7);
			String city = ExcelUtils.getDataAt(i, 8);
			String state = ExcelUtils.getDataAt(i, 9);
			String zip = ExcelUtils.getDataAtNum(i, 10);
			String country = ExcelUtils.getDataAt(i, 11);
			regPage.registerNewUser(id, password, firstName, lastName, mail, phone, address1, address2, city, state, country, zip);
			sa.assertTrue(regPage.isRegistered());
			this.driver.navigate().back();
		}
		sa.assertAll();
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}

}
