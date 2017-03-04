package com.snapdeal.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExternalHardDisksPage extends SnapDealHomePage{

	public ExternalHardDisksPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public String getTitle()
	{
	return	super.getTitle();
	}
	public void filterDisk(String input) throws InterruptedException
	{
		
		WebDriverWait ww=new WebDriverWait(driver, 180);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
			
		List<WebElement>elements=ww.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[div[div[label[a[contains(text(),'4 TB')]]]]]//label")));
	
		for(WebElement each:elements)
		{
			
			if(each.getText().contains(input))
			{
				if(!each.isSelected())
				{
					js.executeScript("arguments[0].click();", each);
					
				}
				
				
				
			}
		
		
	}
	
	}
//	@FindBy(how=How.XPATH,using="//div[div[@class='product-tuple-description ']]//a//p[contains(text(),'1 TB')]")
//	private List<WebElement> lis;
//	
	public boolean getCountof1TB(String input)
	{
		WebDriverWait ww=new WebDriverWait(driver, 180);
		List<WebElement> lis=ww.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[div[@class='product-tuple-description ']]//a//p[contains(text(),'"+input+"')]")));
		if(lis.size()>=1)
		{
			return true;
		}
		return false;
	}
	
	public void closeFilters(String input)
	{
//	List<WebElement>lin=	driver.findElements(By.xpath("//div[@class='navFiltersPill'][a[span[@class='sd-icon sd-icon-delete-sign marL5']]]//a[contains(text(),'1 TB')]"));
//	for(WebElement ea:lin)
//	{
//		ea.click();
//		break;
//		
//	}
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		WebElement ele=driver.findElement(By.xpath("//div[@class='navFiltersPill']//a[contains(text(),'"+input+"')]"));
		js.executeScript("arguments[0].click();", ele);
//	
	
	}


	

}
