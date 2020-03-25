package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PetStoreMenuPage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	
	public PetStoreMenuPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	} 
	
	public WebElement getSignInButton() {
		return this.driver.findElement(By.xpath(locators.getProperty("signIn")));
	}

	public void enterSignInPage() {
		this.getSignInButton().click();
	}
	
	public List<WebElement> getLeftNavMenu(){
		return driver.findElements(By.xpath(locators.getProperty("leftNavMenu")));
	}
	
	public List<WebElement> getCenterNavMenu(){
		return driver.findElements(By.xpath(locators.getProperty("centerNavMenu")));
	}
	
	public List<WebElement> getImageNavMenu(){
		return driver.findElements(By.xpath(locators.getProperty("imageNavMenu")));
	}
	
	
	public int verifyURLStatus(String urlString) {
		int status = 404;
		try {
			URL link = new URL(urlString);
			HttpURLConnection hConn = null;
			hConn = (HttpURLConnection) link.openConnection();
			hConn.setRequestMethod("GET");
			hConn.connect();
			status = hConn.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	
}
