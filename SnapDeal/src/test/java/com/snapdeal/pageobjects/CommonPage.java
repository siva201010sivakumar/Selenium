package com.snapdeal.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonPage {

	
		protected WebDriver driver=null;
		protected CommonPage(WebDriver driver) {
			this.driver=driver;
		}
		protected String getTitle()
		{
			return driver.getTitle();
		}
		
		protected void iclick(WebElement element)
		{
			element.click();
		}
}
