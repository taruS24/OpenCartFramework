package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResultCount = By.cssSelector("div.product-layout");


	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("---getting login page title---")	
	public String pageTitle() {
		return eleUtil.waitForTitleContains(AppConstants.SEARCH_RESULTS_PAGE_TITLE_FRAGMENT,
				AppConstants.SHORT_TIME_OUT);
	}
	
	public int getSearchResultCount(){
		List<WebElement> searchResult = eleUtil.waitForElementsPresence(searchResultCount, AppConstants.MEDIUM_TIME_OUT);
		System.out.println("Search Result gives the following number of products: "+searchResult.size());
		return searchResult.size();
	}
	
	public ProductDescriptionPage selectProduct(String productName){		
		eleUtil.clickElementWhenReady(By.linkText(productName), AppConstants.SHORT_TIME_OUT);
		return new ProductDescriptionPage(driver);		
	}


	
}
