package com.bankbazaar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BankBazaarCommonPage {

	protected WebDriver driver = null;
	protected BankBazaarCommonPage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void click(WebElement element){
		element.click();
	}
	
	public void enterText(WebElement element, String input){
		element.sendKeys(input);
	}
}
