package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	
	public CartPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	} 
	
	public List<WebElement> itemsId() {
		return this.driver.findElements(By.xpath(locators.getProperty("itemIdList")));
	}

	public boolean itemAddedToCart(String id) {
		List<WebElement> itemsId = this.itemsId();
		for(int i = 0; i < itemsId.size(); i++) {
			String itemId = itemsId.get(i).getText();
			if (itemId.contentEquals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public void deleteAllCookies() {
		this.driver.manage().deleteAllCookies();
	}
	
	public boolean isCartEmpty() {
		try {
			this.driver.findElement(By.xpath(locators.getProperty("emptyCart")));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
