package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class CreateAccountSuccessPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By successMsg = By.tagName("h1");

	public CreateAccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getSuccessMsg(){
		 String successMesg = eleUtil.waitForElementVisible(successMsg, AppConstants.MEDIUM_TIME_OUT).getText();
		 System.out.println("Success Message in the registration success page is: "+successMesg);
		 return successMesg;
		 
	}
	
	


}
