package pages;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
	
	public List<WebElement> getItemsId() {
		return this.driver.findElements(By.xpath(locators.getProperty("itemIdList")));
	}
	
	public WebElement getTotalSumElement() {
		return this.driver.findElement(By.xpath(locators.getProperty("sumTotal")));
	}
	
	public double getTotalSum() {
		String subTotal = this.getTotalSumElement().getText().substring(12);
		return Double.parseDouble(subTotal);
	}
	
	public List<WebElement> getAllItemsTotalPrice() {
		try {
			return this.driver.findElements(By.xpath(locators.getProperty("itemsTotalPrice")));
		} catch (Exception e) {
			return null;
		}
	}
	
	public double getTotalItemsPrice() {
		List<WebElement> allItemsPrice = this.getAllItemsTotalPrice();
		double sum = 0;
		if(allItemsPrice != null) {
			for(int i = 0; i < allItemsPrice.size(); i++) {
				String itemPrice = allItemsPrice.get(i).getText().substring(1, 5);
				double price = Double.parseDouble(itemPrice);
				sum += price;
			}
		}
		BigDecimal bd = new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public boolean isPricesEquals() {
		return this.getTotalSum() == this.getTotalItemsPrice();
	}

	public boolean isItemAddedToCart(String id) {
		List<WebElement> itemsId = this.getItemsId();
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
