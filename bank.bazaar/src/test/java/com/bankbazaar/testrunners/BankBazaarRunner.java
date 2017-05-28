package com.bankbazaar.testrunners;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.bankbazaar.pages.BankBazaarCreditCardPage;
import com.bankbazaar.pages.BankBazaarHomePage;
import com.bankbazaar.utils.AppUtil;
import com.bankbazaar.utils.BrowserFactory;
import com.bankbazaar.utils.ExcelUtility;

public class BankBazaarRunner {

	WebDriver driver = null;
	BankBazaarHomePage homePage = null;
	BankBazaarCreditCardPage creditPage = null;
	
	@Parameters({"browsername","url"})
	
	@BeforeMethod
	public void openBrowser(@Optional("chrome") String browsername, @Optional("https://www.bankbazaar.com/") String url){
		
		driver = BrowserFactory.getBrowser(browsername);
		homePage = AppUtil.openURL(driver, url);
	}
	
	@Test(dataProvider = "CreditCardSearchData", dataProviderClass=ExcelUtility.class)
	public void searchCreditCardOffers(String creditCardName, String city, String companyName, String monthlySalary, String salaryBank, String existingCreditCard, String currCreditLimit, String age, String monthYear, String dateValue, String expDOB, String firstName, String expectedMsg, String rowNum) throws Throwable{
		
		String executionStatus = "PASS";
		String errorMessage = "";
		try
		{
			creditPage = homePage.selectProduct();
			creditPage.clickCheckEligibilty(creditCardName);
			creditPage.selectCity(city);
			creditPage.enterCompanyName(companyName);
			int selectedMonSalary = creditPage.selectCurrentMonthlySalary(Integer.parseInt(monthlySalary));
			Assert.assertEquals(selectedMonSalary, Integer.parseInt(monthlySalary));;
			creditPage.selectBank(salaryBank);
			creditPage.selectExistingCreditCard(existingCreditCard);
			int selectedCreditLimit = creditPage.selectCurrCreditLimit(Integer.parseInt(currCreditLimit));
			Assert.assertEquals(selectedCreditLimit, Integer.parseInt(currCreditLimit));
			String fullDOB = creditPage.selectAge(Integer.parseInt(age), monthYear, Integer.parseInt(dateValue));
			Assert.assertEquals(fullDOB, expDOB);
			String searchResultText = creditPage.enterFirstName(firstName);
			Assert.assertEquals(searchResultText, expectedMsg);
			
			/*creditPage = homePage.selectProduct();
			creditPage.clickCheckEligibilty("IndusInd Bank Platinum Aura Edge");
			creditPage.selectCity("Bangalore");
			creditPage.enterCompanyName("IBM DAKSH");
			int selectedMonSalary = creditPage.selectCurrentMonthlySalary(10000);
			Assert.assertEquals(selectedMonSalary, 10000);;
			creditPage.selectBank("HDFC BANK");
			creditPage.selectExistingCreditCard("HSBC");
			int selectedCreditLimit = creditPage.selectCurrCreditLimit(70000);
			Assert.assertEquals(selectedCreditLimit, 70000);
			String fullDOB = creditPage.selectAge(26, "Jan 1991", 15);
			Assert.assertEquals(fullDOB, "15 Jan 1991");
			String searchResultText = creditPage.enterFirstName("FirstAAA");
			Assert.assertEquals(searchResultText, "11 Credit Cards that best suits your needs!");*/
		}
		catch(Throwable t)
		{
			executionStatus = "FAIL";
			errorMessage = t.getMessage();
			throw t; 
		}
		finally
		{
			ExcelUtility.writeToSheetEx1(Integer.parseInt(rowNum), ExcelUtility.CELL_RESULT_COL_NUM, executionStatus);
			ExcelUtility.writeToSheetEx1(Integer.parseInt(rowNum), ExcelUtility.CELL_ERRORMSG_COL_NUM, errorMessage);
		}
	}
	
/*	@AfterMethod
	public void closeBrowser(){
		driver.close();
	}*/
}
