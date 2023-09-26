package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterAccountPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By formHeader = By.tagName("h1");
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By cnfPassword = By.id("input-confirm");
	private By radioYes = By.xpath("//label[normalize-space()='Yes']");
	private By radioNo = By.xpath("//label[normalize-space()='No']");
	private By checkBox = By.xpath("//input[@name='agree']");
	private By submitBtn = By.xpath("//input[@value='Continue']");
	private By successMsg = By.tagName("h1");
	private By logOutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterAccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getRegisterPageTitle() {
		System.out.println("In the Register Page Title method");
		return eleUtil.waitForTitleContains(AppConstants.REGISTER_PAGE_TITLE, AppConstants.MEDIUM_TIME_OUT);
	}

	public String getRegisterPageURL() {
		System.out.println("In the Register Page URL method");
		return eleUtil.waitForURLContains(AppConstants.REGISTER_PAGE_URL_FRACTION, AppConstants.MEDIUM_TIME_OUT);

	}

	public String getRegisterFormHeader() {
		System.out.println("In the Register Page form header method");
		String formHeaderName = eleUtil.waitForElementVisible(formHeader, AppConstants.MEDIUM_TIME_OUT).getText();
		System.out.println("Registration form header name is: " + formHeaderName);
		return formHeaderName;
	}

	public boolean doFillRegistrationForm(String firstNameTxt, String lastNameTxt, String emailTxt,
			String phoneNum, String pwd) {
		System.out.println("In the form filling process....");

		eleUtil.waitForElementVisible(firstName, AppConstants.MEDIUM_TIME_OUT).sendKeys(firstNameTxt);
		eleUtil.getElement(lastName).sendKeys(lastNameTxt);
		eleUtil.getElement(email).sendKeys(emailTxt);
		eleUtil.getElement(telephone).sendKeys(phoneNum);
		eleUtil.getElement(password).sendKeys(pwd);
		eleUtil.getElement(cnfPassword).sendKeys(pwd);
		// driver.findElement(By.xpath("//label[normalize-space()='"+SubscribeYesNo+"']")).click();
		// eleUtil.doClick(radioNo);
		eleUtil.doClick(checkBox);
		eleUtil.doClick(submitBtn);
		String successMessage = eleUtil.waitForElementVisible(successMsg, AppConstants.MEDIUM_TIME_OUT).getText();
		System.out.println("The success message is: "+successMessage);
		if (successMessage.equals(AppConstants.REGISTER_SUCCESS_PAGE_MSG)) {
			eleUtil.doClick(logOutLink);
			eleUtil.doClick(registerLink);
			return true;
		} else return false;
//			return new CreateAccountSuccessPage(driver);
		
		
		
		
		
		

	}

}
