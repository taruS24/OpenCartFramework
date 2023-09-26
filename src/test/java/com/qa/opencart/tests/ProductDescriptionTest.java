package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class ProductDescriptionTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
		// accountsPage.doSearch(searchKey)
	}

	@DataProvider
	public Object[][] searchTestData() {
		return new Object[][] { { "macbook", "MacBook" }, { "macbook", "MacBook Pro" }, { "macbook", "MacBook Air" },
				{ "imac", "iMac" }, { "samsung", "Samsung SyncMaster 941BW" },
				{ "samsung", "Samsung Galaxy Tab 10.1" } };
	}

	@Test(dataProvider = "searchTestData")
	public void productHeaderTest(String searchableProductName, String productHeaderName) {
		searchResultsPage = accountsPage.doSearch(searchableProductName);
		productDescPage = searchResultsPage.selectProduct(productHeaderName);
		String actualProdHeder = productDescPage.getProductHeader();
		Assert.assertEquals(actualProdHeder, productHeaderName);
	}

	@DataProvider
	public Object[][] resultCountData() {
		return new Object[][] { { "macbook", "MacBook", 5 }, { "macbook", "MacBook Pro", 4 },
				{ "macbook", "MacBook Air", 4 }, { "imac", "iMac", 3 }, { "samsung", "Samsung SyncMaster 941BW", 1 },
				{ "samsung", "Samsung Galaxy Tab 10.1", 7 } };
	}

	@Test(dataProvider = "resultCountData")
	public void productImageCountTest(String searchKey, String productName, int count) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		productDescPage = searchResultsPage.selectProduct(productName);
		int prodImgCount = productDescPage.getProductImagesCount();
		Assert.assertEquals(prodImgCount, count);
	}

	@DataProvider
	public Object[][] resultCountDataFromExcel() {
		return ExcelUtil.getSheetData("productTest");
	}

	@Test(dataProvider = "resultCountDataFromExcel")
	public void productImageCountTest(String searchKey, String productName, String count) {
		searchResultsPage = accountsPage.doSearch(searchKey);
		productDescPage = searchResultsPage.selectProduct(productName);
		int prodImgCount = productDescPage.getProductImagesCount();
		Assert.assertEquals(prodImgCount, Integer.parseInt(count));
	}

	@Test
	public void productInfoTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		productDescPage = searchResultsPage.selectProduct("MacBook Pro");
		Map<String, String> completeProductInfo = productDescPage.completeProductInfo();
		System.out.println("Complete product info is as follows: " + completeProductInfo);
		softAssert.assertEquals(completeProductInfo.get("Brand"), "Apple");
		softAssert.assertEquals(completeProductInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(completeProductInfo.get("Product Price"), "$2,000.00");
		softAssert.assertEquals(completeProductInfo.get("Product Name"), "MacBook Pro");
		softAssert.assertEquals(completeProductInfo.get("Ex Tax"), "$2,000.00");
		softAssert.assertEquals(completeProductInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(completeProductInfo.get("Reward Points"), "800");
		softAssert.assertEquals(completeProductInfo.get("Number of Product Images"), "4");
		// This is an important entry as it will assert all the steps and document the
		// failed assertion.
		softAssert.assertAll();
	}

}
