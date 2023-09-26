package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "?route=account/login";

	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final int ACCOUNTS_PAGE_HEADER_COUNT = 4;
	public static final List<String> ACCOUNTS_PAGE_EXPECTED_HEADERS_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");

	public static final String SEARCH_RESULTS_PAGE_TITLE_FRAGMENT = "Search";
	public static final int SEARCH_RESULTS_PAGE_MACBOOK = 3;
	public static final int SEARCH_RESULTS_PAGE_IMAC = 1;
	public static final int SEARCH_RESULTS_PAGE_SAMSUNG = 2;

	public static final String REGISTER_PAGE_TITLE = "Register Account";
	public static final String REGISTER_PAGE_URL_FRACTION = "?route=account/register";
	public static final String REGISTER_PAGE_FORM_HEADER = "Register Account";
	
	public static final String REGISTER_SUCCESS_PAGE_MSG = "Your Account Has Been Created!";

	// ----------------------Default TimeOut values---------------------

	public static final int SHORT_TIME_OUT = 5;
	public static final int MEDIUM_TIME_OUT = 10;
	public static final int LONG_TIME_OUT = 20;
	
	
	//------------------------------Excel sheet Data-----------------------
	
	public static final String REGISTER_SHEET_NAME = "register"; 

}
