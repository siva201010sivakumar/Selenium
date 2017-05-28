package com.bankbazaar.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BankBazaarHomePage extends BankBazaarCommonPage{

	public BankBazaarHomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how=How.XPATH, using="//div[contains(@class,'product pull-left')]//button")
	private WebElement eleProdDropDown;
	
	@FindBy(how=How.XPATH, using="//li//span[contains(text(),'Credit Card')]")
	private WebElement eleCreditCard;
	
	public BankBazaarCreditCardPage selectProduct(){
		
		click(eleProdDropDown);
		click(eleCreditCard);
		
		return PageFactory.initElements(driver, BankBazaarCreditCardPage.class);
	}
}
