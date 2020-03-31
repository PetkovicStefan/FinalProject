package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	
	public SignInPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	} 
	
	public WebElement getUserName() {
		return this.driver.findElement(By.xpath(locators.getProperty("username")));
	}
	
	public void setUserName(String username) {
		WebElement user = this.getUserName();
		user.clear();
		user.sendKeys(username);
	}
	
	public WebElement getPassword() {
		return this.driver.findElement(By.xpath(locators.getProperty("password")));
	}
	
	public void setPassword(String password) {
		WebElement pass = this.getPassword();
		pass.clear();
		pass.sendKeys(password);
	}
	
	public WebElement getLoginButton() {
		return this.driver.findElement(By.xpath(locators.getProperty("loginButton")));
	}
	
	public void clickLoginButton() {
		this.getLoginButton().click();
	}
	public WebElement getRegisterNow() {
		return this.driver.findElement(By.xpath(locators.getProperty("registerNow")));
	}
	
	public void clickRegisterNow() {
		this.getRegisterNow().click();
	}
	
	public void signIn(String username, String password) {
		this.setUserName(username);
		this.setPassword(password);
		this.clickLoginButton();
	}
	
	public WebElement getSignOut() {
		return this.driver.findElement(By.xpath(locators.getProperty("signOut")));
	}
	
	public boolean isSignedIn() {
		try {
			this.getSignOut();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void signOut() {
		this.getSignOut().click();
	}
	

}
