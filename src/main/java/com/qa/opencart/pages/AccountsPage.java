package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	By pageHeaders = By.tagName("h2");
	By logOutLink = By.linkText("Logout");
	By searchBar = By.name("search");
	By searchIcon = By.xpath("//div[@id='search']//button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String verifyPageTitle() {
		String pageTitle = eleUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Accounts Page title is: " + pageTitle);
		return pageTitle;
	}

	public boolean ifLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logOutLink, AppConstants.SHORT_TIME_OUT).isDisplayed();
	}

	public int verifyAccountsPageHeadersCount() {
		return eleUtil.waitForElementsVisible(pageHeaders, AppConstants.SHORT_TIME_OUT).size();
	}

	public List<String> verifyAccountsPageHeaderNames() {
		List<WebElement> headersList = eleUtil.waitForElementsPresence(pageHeaders, AppConstants.SHORT_TIME_OUT);
		ArrayList<String> headerNames = new ArrayList<String>();
		for (WebElement e : headersList) {
			String header = e.getText();
			headerNames.add(header);
		}
		System.out.println("Actual headers of the accounts page: " + headersList);
		return headerNames;
	}

	public SearchResultsPage doSearch(String searchKey) {

		eleUtil.waitForElementPresence(searchBar, AppConstants.MEDIUM_TIME_OUT).clear();
		eleUtil.doSendKeys(searchBar, searchKey);
		// eleUtil.waitForElementPresence(searchBar,
		// AppConstants.MEDIUM_TIME_OUT).sendKeys(searchKey) ;
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}
}
