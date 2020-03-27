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
//		return waiter.until(ExpectedConditions.elementToBeClickable(By.xpath(locators.getProperty("loginButton")))).isDisplayed();
		return this.driver.findElement(By.xpath(locators.getProperty("loginButton"))).isDisplayed();
	}
	
	public List<WebElement> getLeftNavMenu(){
		return driver.findElements(By.xpath(locators.getProperty("leftNavMenu")));
	}
	
	public boolean urlLeftNavMenuWorks() {
		List<WebElement> leftNavMenu = this.getLeftNavMenu();
		for(int i = 0; i < leftNavMenu.size(); i++) {
			int status = this.verifyURLStatus(leftNavMenu.get(i).getAttribute("href"));
			if(status > 400) {
				return false;
			}
		}
		return true;
	}
	
	public boolean leftNavMenuCorrectPage() {
		List<WebElement> leftNavMenu = this.getLeftNavMenu();
		boolean correctPage = true;
		for(int i = 0; i < leftNavMenu.size(); i++) {
			
			String href = leftNavMenu.get(i).getAttribute("href");
			leftNavMenu.get(i).click();
			String petName = this.driver.findElement(By.xpath(locators.getProperty("petCategory"))).getText();
			petName = petName.toUpperCase();
			if(!href.contains(petName)) {
				correctPage = false;
				break;
			}
			this.driver.navigate().back();
			leftNavMenu = this.getLeftNavMenu();
		}
		return correctPage;
	}
	
	public List<WebElement> getCenterNavMenu(){
		return driver.findElements(By.xpath(locators.getProperty("centerNavMenu")));
	}
	
	public boolean urlCenterNavMenuWorks() {
		List<WebElement> centerNavMenu = this.getCenterNavMenu();
		for(int i = 0; i < centerNavMenu.size(); i++) {
			int status = this.verifyURLStatus(centerNavMenu.get(i).getAttribute("href"));
			if(status > 400) {
				return false;
			}
		}
		return true;
	}
	
	public boolean centerNavMenuCorrectPage() {
		List<WebElement> centerNavMenu = this.getCenterNavMenu();
		boolean correctPage = true;
		for(int i = 0; i < centerNavMenu.size(); i++) {
			
			String href = centerNavMenu.get(i).getAttribute("href");
			centerNavMenu.get(i).click();
			String petName = this.driver.findElement(By.xpath(locators.getProperty("petCategory"))).getText();
			petName = petName.toUpperCase();
			if(!href.contains(petName)) {
				correctPage = false;
				break;
			}
			this.driver.navigate().back();
			centerNavMenu = this.getCenterNavMenu();
		}
		return correctPage;
	}
	
	public List<WebElement> getImageNavMenu(){
		return driver.findElements(By.xpath(locators.getProperty("imageNavMenu")));
	}
	
	public boolean urlImageNavMenuWorks() {
		List<WebElement> imageNavMenu = this.getImageNavMenu();
		for(int i = 0; i < imageNavMenu.size(); i++) {
			int status = this.verifyURLStatus(imageNavMenu.get(i).getAttribute("href"));
			if(status > 400) {
				return false;
			}
		}
		return true;
	}
	
	public boolean imageNavMenuCorrectPage() {
		List<WebElement> imageNavMenu = this.getImageNavMenu();
		boolean correctPage = true;
		for(int i = 0; i < imageNavMenu.size(); i++) {
			
			String href = imageNavMenu.get(i).getAttribute("href");
			imageNavMenu.get(i).click();
			String petName = this.driver.findElement(By.xpath(locators.getProperty("petCategory"))).getText();
			petName = petName.toUpperCase();
			if(!href.contains(petName)) {
				correctPage = false;
				break;
			}
			this.driver.navigate().back();
			imageNavMenu = this.getImageNavMenu();
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
