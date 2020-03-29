package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import pages.CartPage;
import pages.StoreItemPage;
import utils.ExcelUtils;

public class TestCartPage {
	
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
		
	}
	
	@Test
	public void isItemsAddedToCart() {
		StoreItemPage itemPage = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);
		for(int i = 1; i <  ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);
			String itemUrl = ExcelUtils.getDataAt(i, 1);
			
			this.driver.navigate().to(itemUrl);
			itemPage.clickAddToCart();
			
			sa.assertTrue(cart.itemAddedToCart(itemId));
		}
		sa.assertAll();
	}
	
	@Test
	public void isCookiesWorks() throws InterruptedException {
		StoreItemPage itemPage = new StoreItemPage(driver, locators, waiter);
		CartPage cart = new CartPage(driver, locators, waiter);
		SoftAssert sa = new SoftAssert();
		
		ExcelUtils.setExcell("data/pet-store-data.xlsx");
		ExcelUtils.setWorkSheet(0);
		for(int i = 1; i <  ExcelUtils.getRowNumber(); i++) {
			String itemId = ExcelUtils.getDataAt(i, 0);
			String itemUrl = ExcelUtils.getDataAt(i, 1);
			
			this.driver.navigate().to(itemUrl);
			itemPage.clickAddToCart();
		}
		this.driver.navigate().to(locators.getProperty("cartUrl"));
		Thread.sleep(2000);
		cart.deleteAllCookies();
		this.driver.navigate().refresh();
		Assert.assertTrue(cart.isCartEmpty());
	}
	
	@AfterClass
	public void afterClass() {
		this.driver.close();
	}
}
