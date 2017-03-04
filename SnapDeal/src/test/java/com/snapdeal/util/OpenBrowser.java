package com.snapdeal.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.snapdea.constants.Constants;

public class OpenBrowser {

	
		public static WebDriver getBrowser(String browser)
		{
			if(browser.equalsIgnoreCase(Constants.CHROME.toString()))
			{
				ChromeDriver driver=new ChromeDriver();
				return driver;
			}
			else if(browser.equalsIgnoreCase(Constants.IE.toString()))
			{
				InternetExplorerDriver driver=new InternetExplorerDriver();
				return driver;
			}
			else return null;
		}
}
