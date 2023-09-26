package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 
 * @author taru.sharma
 *
 */
public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This is used to initialize the driver
	 * 
	 * @param browserName
	 * @return it returns the driver
	 */
	public WebDriver initDriver(Properties prop) {
		System.out.println("In the initDriver method of the DriverFactory class");
		// mvn clean install -Dbrowser="chrome"
//		String browserName = System.getProperty("browser");

		String browserName = prop.getProperty("browser");
		String url = prop.getProperty("URL");
		System.out.println("URL is: "+url);
		System.out.println("Browser name is: " + browserName);
		
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");

		switch (browserName.toLowerCase()) {
		case "chrome":
//			driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
//			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;

		case "edge":
//			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println("Please provide a valid driver. Driver provided is: " + browserName);
			break;
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(url);

		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();

	}

	/**
	 * This method is used to initialize properties in config.properties file
	 * 
	 * @return Properties reference
	 */
	// './' means go to the project directory
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fis = null;

		// mvn clean install -Denv="qa"

		String envName = System.getProperty("env");
		System.out.println("Environment is: " + envName);

		try {
			if (envName == null) {
				System.out.println("No Env found, running in QA Env by default");
				fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
			}

			else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					System.out.println("Running in QA Env");
					fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					System.out.println("Running in Dev Env");
					fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					System.out.println("Running in Stage Env");
					fis = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;

				default:
					System.out.println("Please pass the right environment...." + envName);
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName+"_"+System.currentTimeMillis() + ".png";

//	"new File" Creates a new File instance by converting the given pathname string into an abstract pathname
		File destinationFile = new File(path);
		try {
			FileHandler.copy(srcFile, destinationFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
