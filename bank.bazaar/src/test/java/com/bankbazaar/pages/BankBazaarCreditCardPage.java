package com.bankbazaar.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BankBazaarCreditCardPage extends BankBazaarCommonPage{

	public BankBazaarCreditCardPage(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	JavascriptExecutor jse = (JavascriptExecutor)driver;
	Actions act = new Actions(driver);
	
	@FindBy(how = How.XPATH, using = "//li//span[contains(@class,'js-text')]")
	private List<WebElement> eleCreditCardList;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@for,'BANGALORE')]")
	private WebElement eleCityName;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@name,'companyName')]")
	private WebElement eleCompanyName;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'arrow-right')]")
	private WebElement eleRightArrow;
	
	@FindBy(how = How.XPATH, using = "//button/span[text()='Select your bank']")
	private WebElement eleSelectYourBankDD;
	
	@FindBy(how = How.XPATH, using = "//div[@slidename='salarysavingsaccount']//li")
	private List<WebElement> eleBankNames;
	
	@FindBy(how = How.XPATH, using = "//label[contains(@for,'existingCCBankId')]//span[@class='eform-info']")
	private List<WebElement> eleExistingCreditCards;
	
	@FindBy(how = How.XPATH, using = "//div[contains(@id,'multiSelectDropDown')]//li")
	private WebElement eleViewMoreBankNames;
	
	@FindBy(how = How.XPATH, using = "//input[@name='form.details.applicant.firstName']")
	private WebElement eleTextFirstName;
	
	@FindBy(how = How.XPATH, using = "//div[@data-actionloc='slideElig:contactme']//input[@value='View FREE Offers']")
	private WebElement eleBtnViewFreeOffers;
	
	public void clickCheckEligibilty(String cardName){
		String chkElegibilityXPATH="";
		for(int i = 0; i < eleCreditCardList.size();i++)
		{
			if(eleCreditCardList.get(i).getText().toUpperCase().contains(cardName.toUpperCase()))
			{
				chkElegibilityXPATH = "//section[@offerindex='"+(i+1)+"']//div[span//span[contains(text(),'"+cardName+"')]]//following-sibling::div//a[@alt='Check Eligibility']";
				break;
			}
		}
		WebElement eleChkElegibility = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(chkElegibilityXPATH)));
		click(eleChkElegibility);
	}
	
	public void selectCity(String cityName){
		String cityNameXpath = "//label[contains(@for,'"+(cityName.toUpperCase())+"')]";
		eleCityName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(cityNameXpath)));
		click(eleCityName);
	}
	
	public void enterCompanyName(String companyName){
		eleCompanyName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'companyName')]")));
		enterText(eleCompanyName, companyName);
		eleRightArrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'arrow-right')]")));
		click(eleRightArrow);
		//act.sendKeys(Keys.ENTER).build().perform();
	}
	
	public int selectCurrentMonthlySalary(int monthlySalary){
		
		Action dragAndDrop;
		WebElement slider= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@slidename='salary' and @class='item active']//div[@class='slider-handle round']")));
		WebElement eleSalTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-action='slideStop:edit' and @data-ac	tionloc='slideElig:salary-slider']//div[@id='tooltip']/div[@class='tooltip-inner']")));
		int slideMonthlySalary = Integer.parseInt(eleSalTextbox.getText());
		//int monthlySalary = Integer.parseInt(salary);
		if(monthlySalary>=120000)
		{
			dragAndDrop = act.clickAndHold(slider).moveByOffset(610,0).release().build();
			dragAndDrop.perform();
		}
		else
		{
			while(slideMonthlySalary<monthlySalary)
			{
				dragAndDrop = act.clickAndHold(slider).moveByOffset(6,0).release().build();
				dragAndDrop.perform();
				eleSalTextbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-action='slideStop:edit' and @data-actionloc='slideElig:salary-slider']//div[@id='tooltip']/div[@class='tooltip-inner']")));
				slideMonthlySalary = Integer.parseInt(eleSalTextbox.getText().replaceAll(",", ""));
				
			}
		}
		WebElement eleSelMonSalary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='form.details.applicant.income.monthlyTakeHomeSalary']")));
		//System.out.println(eleDOB.getAttribute("value").trim().replaceAll(",", ""));
		eleRightArrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'arrow-right')]")));
		eleRightArrow.click();
		return Integer.parseInt(eleSelMonSalary.getAttribute("value").trim().replaceAll(",", ""));
	}
	
	public void selectBank(String bankName){
		eleSelectYourBankDD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Select your bank']")));
		click(eleSelectYourBankDD);
		String bankNameXpath = "//div[@slidename='salarysavingsaccount']//li//span[contains(text(),'"+bankName+"')]";
		WebElement eleBankName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(bankNameXpath)));
		click(eleBankName);
		eleRightArrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'arrow-right')]")));
		click(eleRightArrow);
	}
	
	public void selectExistingCreditCard(String bankName){
		eleExistingCreditCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//label[contains(@for,'existingCCBankId')]//span[@class='eform-info']")));
		int visibleBankNameFlag = 0;
		for(int i=0;i<eleExistingCreditCards.size();i++)
		{
			if(eleExistingCreditCards.get(i).getAttribute("textContent").equalsIgnoreCase(bankName))
			{
				wait.until(ExpectedConditions.visibilityOf(eleExistingCreditCards.get(i))).click();
				visibleBankNameFlag = 1;
				break;
			}
		}
		if(visibleBankNameFlag==0)
		{
			eleViewMoreBankNames = wait.until(ExpectedConditions.visibilityOf(eleExistingCreditCards.get(eleExistingCreditCards.size()-1)));
			eleViewMoreBankNames.click();
			List<WebElement> eleMoreBankNames = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@id,'multiSelectDropDown')]//li")));
			for(int i=0;i<eleMoreBankNames.size();i++)
			{
				if(eleMoreBankNames.get(i).getAttribute("textContent").trim().equalsIgnoreCase(bankName))
				{
					wait.until(ExpectedConditions.visibilityOf(eleMoreBankNames.get(i))).click();
					break;
				}
			}
		}
		eleRightArrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'arrow-right')]")));
		click(eleRightArrow);
	}
	
	public int selectCurrCreditLimit(int curCreditLimit){
		Action dragAndDrop;
		WebElement slider= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@slidename='maximumcredit' and @class='item active']//div[@class='slider-handle round']")));
		WebElement eleCurrCreditTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-action='slideStop:edit' and @data-actionloc='slideElig:maximumcredit-slider']//div[@id='tooltip']/div[@class='tooltip-inner']")));
		//dragAndDrop = act.clickAndHold(slider).moveByOffset(612,0).release().build();dragAndDrop.perform();
		int slideCurrCreditLimit = Integer.parseInt(eleCurrCreditTextbox.getText());
		
		if(curCreditLimit>=500000)
		{
			dragAndDrop = act.clickAndHold(slider).moveByOffset(612,0).release().build();
			dragAndDrop.perform();
		}
		else
		{
			while(slideCurrCreditLimit<curCreditLimit)
			{
				dragAndDrop = act.clickAndHold(slider).moveByOffset(12,0).release().build();
				dragAndDrop.perform();
				eleCurrCreditTextbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-action='slideStop:edit' and @data-actionloc='slideElig:maximumcredit-slider']//div[@id='tooltip']/div[@class='tooltip-inner']")));
				slideCurrCreditLimit = Integer.parseInt(eleCurrCreditTextbox.getText().replaceAll(",", ""));
			}
		}
		WebElement eleSelCreditLimit = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='form.details.applicant.income.maximumCreditOfExistingCards']")));
		//System.out.println(eleDOB.getAttribute("value").trim().replaceAll(",", ""));
		eleRightArrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'arrow-right')]")));
		eleRightArrow.click();
		return Integer.parseInt(eleSelCreditLimit.getAttribute("value").trim().replaceAll(",", ""));
	}
	
	public String selectAge(int age, String monthYear, int date){
		Action dragAndDrop;
		WebElement slider= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@slidename='dob' and @class='item active']//div[@class='slider-handle round']")));
		WebElement eleAgeTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-action='slideStop:edit' and @data-actionloc='slideElig:dob-slider']//div[@id='tooltip']/div[@class='tooltip-inner']")));
		//dragAndDrop = act.clickAndHold(slider).moveByOffset(395,0).release().build();dragAndDrop.perform();
		int slideAge = Integer.parseInt(eleAgeTextbox.getText());
		if(age>=70)
		{
			dragAndDrop = act.clickAndHold(slider).moveByOffset(400,0).release().build();
			dragAndDrop.perform();
		}
		else
		{
			while(slideAge<age)
			{
				dragAndDrop = act.clickAndHold(slider).moveByOffset(7,0).release().build();
				dragAndDrop.perform();
				eleAgeTextbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-action='slideStop:edit' and @data-actionloc='slideElig:dob-slider']//div[@id='tooltip']/div[@class='tooltip-inner']")));
				slideAge = Integer.parseInt(eleAgeTextbox.getText().replaceAll(",", ""));
			}
		}
		
		List<WebElement> eleMonthYear = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@data-actionloc='slideElig:dob-month']//tr/td")));
		for(int i=0;i<eleMonthYear.size();i++)
		{
			if(eleMonthYear.get(i).getAttribute("textContent").equalsIgnoreCase(monthYear))
			{
				wait.until(ExpectedConditions.elementToBeClickable(eleMonthYear.get(i))).click();
				break;
			}
		}
		
		String dateNumber = ""+date;
		List<WebElement> eleDay = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='ui-datepicker-calendar']//tr/td")));
		for(int i=0;i<eleDay.size();i++)
		{
			if(eleDay.get(i).getAttribute("textContent").equalsIgnoreCase(dateNumber))
			{
				wait.until(ExpectedConditions.elementToBeClickable(eleDay.get(i))).click();
				break;
			}
		}
		WebElement eleDOB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='dob']")));
		//System.out.println(eleDOB.getAttribute("value").trim());
		eleRightArrow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class,'arrow-right')]")));
		eleRightArrow.click();
		return eleDOB.getAttribute("value").trim();
	}
	
	public String enterFirstName(String firstName){
		eleTextFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='form.details.applicant.firstName']")));
		enterText(eleTextFirstName, firstName);
		eleBtnViewFreeOffers = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-actionloc='slideElig:contactme']//input[@value='View FREE Offers']")));
		eleBtnViewFreeOffers.click();
		WebElement eleSearchResultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='js-callout-text']")));
		String searchResultText = eleSearchResultText.getAttribute("textContent").trim();
		//System.out.println(searchResultText);
		
		return searchResultText;
	}
}
