package com.snapdeal.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.snapdeal.pageobjects.SnapDealHomePage;

public class OpenUrl {
	
	public static SnapDealHomePage getUrl(String url,WebDriver driver)
	{
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		return PageFactory.initElements(driver, SnapDealHomePage.class);
	}
}
