package com.bankbazaar.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.bankbazaar.pages.BankBazaarHomePage;

public class AppUtil {

	public static BankBazaarHomePage openURL(WebDriver driver,String url){
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to(url);
		
		return PageFactory.initElements(driver, BankBazaarHomePage.class);
	}
}
