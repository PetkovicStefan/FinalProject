package pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
	
	private WebDriver driver;
	private Properties locators;
	private WebDriverWait waiter;
	
	public RegistrationPage(WebDriver driver, Properties locators, WebDriverWait waiter) {
		this.driver = driver;
		this.locators = locators;
		this.waiter = waiter;
	} 
	
	public WebElement getUserInformation() {
		return this.driver.findElement(By.xpath(locators.getProperty("userInformationTable")));
	}
	
	public WebElement getId() {
		return this.getUserInformation().findElement(By.xpath(locators.getProperty("id")));
	}
	
	public void setId(String newId) {
		WebElement id = this.getId();
		id.clear();
		id.sendKeys(newId);
	}
	
	public WebElement getNewPassword() {
		return this.getUserInformation().findElement(By.xpath(locators.getProperty("newPassword")));
	}
	
	public void setNewPassword(String newPassword) {
		WebElement password = this.getNewPassword();
		password.clear();
		password.sendKeys(newPassword);
	}
	
	public WebElement getRepeatPassword() {
		return this.getUserInformation().findElement(By.xpath(locators.getProperty("repeatPassword")));
	}
	
	public void setRepeatPassword(String newPassword) {
		WebElement rPassword = this.getRepeatPassword();
		rPassword.clear();
		rPassword.sendKeys(newPassword);
	}
	
	public WebElement getAccountInformation() {
		return this.driver.findElement(By.xpath(locators.getProperty("accountInformationTable")));
	}
	
	public WebElement getFirstName() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("firstName")));
	}
	
	public void setFirstName(String firstName) {
		WebElement name = this.getFirstName();
		name.clear();
		name.sendKeys(firstName);
	}
	
	public WebElement getLastName() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("lastName")));
	}
	
	public void setLastName(String lastName) {
		WebElement lName = this.getLastName();
		lName.clear();
		lName.sendKeys(lastName);
	}
	
	public WebElement getEmail() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("email")));
	}
	
	public void setEmail(String mail) {
		WebElement email = this.getEmail();
		email.clear();
		email.sendKeys(mail);
	}
	
	public WebElement getPhone() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("phone")));
	}
	
	public void setPhone(String newPhone) {
		WebElement phone = this.getPhone();
		phone.clear();
		phone.sendKeys(newPhone);
	}
	
	public WebElement getAddress1() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("address1")));
	}
	
	public void setAddress1(String newAddress) {
		WebElement address = this.getAddress1();
		address.clear();
		address.sendKeys(newAddress);
	}
	
	public WebElement getAddress2() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("address2")));
	}
	
	public void setAddress2(String newAddress2) {
		WebElement address = this.getAddress2();
		address.clear();
		address.sendKeys(newAddress2);
	}
	
	public WebElement getCity() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("city")));
	}
	
	public void setCity(String newCity) {
		WebElement city = this.getCity();
		city.clear();
		city.sendKeys(newCity);
	}
	
	public WebElement getState() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("state")));
	}
	
	public void setState(String newState) {
		WebElement state = this.getState();
		state.clear();
		state.sendKeys(newState);
	}
	
	public WebElement getZip() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("zip")));
	}
	
	public void setZip(String newZip) {
		WebElement zip = this.getZip();
		zip.clear();
		zip.sendKeys(newZip);
	}
	
	public WebElement getCountry() {
		return this.getAccountInformation().findElement(By.xpath(locators.getProperty("country")));
	}
	
	public void setCountry(String newCountry) {
		WebElement country = this.getCountry();
		country.clear();
		country.sendKeys(newCountry);
	}
	
	public WebElement getSaveButton() {
		return this.driver.findElement(By.xpath(locators.getProperty("saveAccountButton")));
	}
	
	public void clickSaveButton() {
		this.getSaveButton().click();
	}

}
