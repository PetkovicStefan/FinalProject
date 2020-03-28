package pages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	public boolean isEnteredSignInPage() {
		this.enterSignInPage();
		return waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locators.getProperty("loginButton")))).isDisplayed();
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
	
	public boolean urlMenuWorks(List<WebElement> navigationMenu) {
		List<WebElement> navMenu = navigationMenu;
		for(int i = 0; i < navMenu.size(); i++) {
			int status = this.verifyURLStatus(navMenu.get(i).getAttribute("href"));
			if(status > 400) {
				return false;
			}
		}
		return true;
	}
	
	public boolean leftNavMenuCorrectPage() {
		List<WebElement> navMenu = this.getLeftNavMenu();
		boolean correctPage = true;
		for(int i = 0; i < navMenu.size(); i++) {
	
			String href = navMenu.get(i).getAttribute("href");
			navMenu.get(i).click();
			String petName = this.driver.findElement(By.xpath(locators.getProperty("petCategory"))).getText();
			petName = petName.toUpperCase();
			if(!href.contains(petName)) {
				correctPage = false;
				break;
			}
			this.driver.navigate().back();
			navMenu = this.getLeftNavMenu();
		}
		return correctPage;
	}
	
	public boolean centerNavMenuCorrectPage() {
		List<WebElement> navMenu = this.getCenterNavMenu();
		boolean correctPage = true;
		for(int i = 0; i < navMenu.size(); i++) {
	
			String href = navMenu.get(i).getAttribute("href");
			navMenu.get(i).click();
			String petName = this.driver.findElement(By.xpath(locators.getProperty("petCategory"))).getText();
			petName = petName.toUpperCase();
			if(!href.contains(petName)) {
				correctPage = false;
				break;
			}
			this.driver.navigate().back();
			navMenu = this.getCenterNavMenu();
		}
		return correctPage;
	}
	
	public boolean imageNavMenuCorrectPage() {
		List<WebElement> navMenu = this.getImageNavMenu();
		boolean correctPage = true;
		for(int i = 0; i < navMenu.size(); i++) {
	
			String href = navMenu.get(i).getAttribute("href");
			navMenu.get(i).click();
			String petName = this.driver.findElement(By.xpath(locators.getProperty("petCategory"))).getText();
			petName = petName.toUpperCase();
			if(!href.contains(petName)) {
				correctPage = false;
				break;
			}
			this.driver.navigate().back();
			navMenu = this.getImageNavMenu();
		}
		return correctPage;
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
