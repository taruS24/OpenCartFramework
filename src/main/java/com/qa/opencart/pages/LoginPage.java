package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators
	private By email_id = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By reisterLink = By.linkText("Register");

	// 2. public page constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// public page action methods
	public String getLoginPageTitle() {
		String pageTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
//		String pageTitle = driver.getTitle();
			System.out.println("Login Page title is: " + pageTitle);
			return pageTitle;
	}

	public String getLoginPageURL() {
		String pageURL = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page URL is: " + pageURL);
		return pageURL;
	}

	public boolean isForgotPasswordLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
//		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	@Step("Login to the application using username:{0} and password: {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		System.out.println("The login credeintilas are: " + userName + " and " + pwd);
		eleUtil.waitForElementVisible(email_id, AppConstants.SHORT_TIME_OUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterAccountPage goToReistrationPage(){
		System.out.println("On the way to click the 'Register' link on the login page");
		eleUtil.clickElementWhenReady(reisterLink, AppConstants.MEDIUM_TIME_OUT);
		return new RegisterAccountPage(driver);
	}
}
