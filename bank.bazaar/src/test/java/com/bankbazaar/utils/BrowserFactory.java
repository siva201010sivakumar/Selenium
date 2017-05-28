package com.bankbazaar.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.bankbazaar.constants.BrowserConstants;

public class BrowserFactory {

public static WebDriver driver = null;
	
	public static WebDriver getBrowser(String browserName){
		
		if(browserName.equalsIgnoreCase(BrowserConstants.CHROME.toString()))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase(BrowserConstants.FIREFOX.toString()))
		{
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase(BrowserConstants.IE.toString()))
		{
			driver = new InternetExplorerDriver(); 
		}
		else 
		{
			driver = new PhantomJSDriver();
		}
		return driver;
	}
}
