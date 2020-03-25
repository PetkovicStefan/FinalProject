package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	
	public HomePage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	} 
	
	public WebElement getEnterStoreButton() {
		return this.driver.findElement(By.xpath(locators.getProperty("buttonEnterTheStore")));
	}
	
	public void enterTheStore() {
		this.getEnterStoreButton().click();
	}
	
	public boolean isEnter() {
		return this.driver.findElement(By.xpath(locators.getProperty("signIn"))).isDisplayed();
	}
	
}
