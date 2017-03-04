package com.snapdeal.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SnapDealHomePage extends CommonPage{

	public SnapDealHomePage(WebDriver driver) {
		super(driver);
		
	}
	
	//@FindBy(how=How.XPATH,using="//div[ul[li[@class='navlink']]]//li[contains(.,'Computers')]")
	//private WebElement ele_ComputersGaming;
	public ExternalHardDisksPage cickOnComputersGaming()
	{
		
		
		WebDriverWait ww=new WebDriverWait(driver, 180);
		WebElement ele=ww.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[@class='nav smallNav']//li//span[starts-with(text(),'Computers')]")));
		
		super.iclick(ele);
//		List<WebElement> ele2=ele.findElements(By.xpath("//div[@class='colDataInnerBlk']/p/a/span"));
//		for(WebElement ele3:ele2)
//		{
//			System.out.println(ele3.getText());
//		}
		ele.findElement(By.xpath("//div[@class='colDataInnerBlk']/p/a/span[contains(text(),'External Hard')]")).click();
		return PageFactory.initElements(driver, ExternalHardDisksPage.class);
		
		
	}
	
	
	
	

}
