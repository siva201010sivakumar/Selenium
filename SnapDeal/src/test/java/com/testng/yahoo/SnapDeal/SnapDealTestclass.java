package com.testng.yahoo.SnapDeal;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.snapdeal.pageobjects.ExternalHardDisksPage;
import com.snapdeal.pageobjects.SnapDealHomePage;
import com.snapdeal.util.CommonUtil;
import com.snapdeal.util.OpenBrowser;
import com.snapdeal.util.OpenUrl;

import junit.framework.Assert;

public class SnapDealTestclass {

		WebDriver driver=null;
		SnapDealHomePage hpage=null;
		ExternalHardDisksPage expage=null;
	@BeforeMethod
	public void prequisites() throws IOException
	{
		//1.open the browser
		  driver=OpenBrowser.getBrowser(CommonUtil.getPropertyValue("config", "browser"));
		 
		 //2.open url
		 hpage= OpenUrl.getUrl(CommonUtil.getPropertyValue("config", "url"), driver);
	}
	
	@AfterMethod
	public void closeBrowser()
	{
		driver.quit();
	}
	 /**
	 * @throws IOException
	 * @author siva
	 * @throws InterruptedException 
	 */
	@Test
	 public void verifyExistenceOfOffers() throws IOException, InterruptedException
	 {
		 
		//3.cick on the computers and gaming and external hardrives link
		expage=hpage.cickOnComputersGaming();
		
		//4.verifying the expected homepage was launched or not
		Assert.assertEquals(CommonUtil.getPropertyValue("externalpage", "expected"), expage.getTitle());
		
		//5.giving filter to 1TB
		expage.filterDisk(CommonUtil.getPropertyValue("externalpage", "input1"));
		
		//6.giving filter to 500GB
		
		expage.filterDisk(CommonUtil.getPropertyValue("externalpage", "input2"));
		
		//7.getting the count and verifying the atleast 1 TB is there in search results or not
		
		Assert.assertTrue(expage.getCountof1TB(CommonUtil.getPropertyValue("externalpage", "input1")));
		
		//8.closing the flter 1 TB
		
		expage.closeFilters(CommonUtil.getPropertyValue("externalpage", "input1"));
		
		//9.verifying the atleast  one 500gb search reslts are there
		
		Assert.assertTrue(expage.getCountof1TB(CommonUtil.getPropertyValue("externalpage", "input2")));
		
		//10.cosing the filter 500Gb
		expage.closeFilters(CommonUtil.getPropertyValue("externalpage", "input2"));
		
	
		
	 }
}
