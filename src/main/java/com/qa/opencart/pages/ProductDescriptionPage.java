package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductDescriptionPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productName = By.tagName("h1");
	private By imageCount = By.xpath("//ul[@class='thumbnails']//img");
	private By addToCartBtn = By.id("button-cart");
	private By productMetaData = By.xpath("(//h1/parent::div//ul)[1]/li");
	private By productPriceData = By.xpath("(//h1/parent::div//ul)[2]/li");

	Map<String, String> productMap;

	public ProductDescriptionPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String productHeader = eleUtil.waitForElementVisible(productName, AppConstants.MEDIUM_TIME_OUT).getText();
		System.out.println("Product is ==> " + productHeader);
		return productHeader;
	}

	public int getProductImagesCount() {
		int prodCount = eleUtil.waitForElementsVisible(imageCount, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println(" Actual Product images are: " + prodCount);
		return prodCount;
	}

	private void getProductMetaData() {
		List<WebElement> productDataList = eleUtil.waitForElementsVisible(productMetaData,
				AppConstants.MEDIUM_TIME_OUT);
		// Map<String, String> productDataMap = new HashMap<String, String>();
		for (WebElement e : productDataList) {
			String productData = e.getText();
			String DataKey = productData.split(":")[0].trim();
			String DataValue = productData.split(":")[1].trim();
			productMap.put(DataKey, DataValue);
		}

	}

	private void getProductPricing() {
		List<WebElement> productPriceList = eleUtil.waitForElementsVisible(productPriceData, AppConstants.MEDIUM_TIME_OUT);
		// Map<String, String> productPriceMap = new HashMap<String, String>();
		String actualPrice = productPriceList.get(0).getText().trim();
		String exTax = productPriceList.get(1).getText().split(":")[0].trim();
		String exTaxValue = productPriceList.get(1).getText().split(":")[1].trim();
		productMap.put("Product Price", actualPrice);
		productMap.put(exTax, exTaxValue);
	}

	public Map<String, String> completeProductInfo() {
		productMap = new HashMap<>();
		productMap.put("Product Name", getProductHeader());
		getProductMetaData();
		getProductPricing();
		productMap.put("Number of Product Images", String.valueOf((getProductImagesCount())));
		
		return productMap;
	}

}
