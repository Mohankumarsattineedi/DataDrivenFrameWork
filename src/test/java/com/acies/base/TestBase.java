package com.acies.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.acies.utilities.Excelutil;

public class TestBase {

	/*
	 * WebDriver Properties Logs - .logs , log4j.properties , Logger Extent
	 * Reports DB Excel data Mail
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger(TestBase.class);
	public static Excelutil excel = new Excelutil();
	public static WebDriverWait wait;
	

	@BeforeSuite
	public void setUp() throws FileNotFoundException {

		if (driver == null) {

			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/Config.Properties");

			try {
				config.load(fis);
				log.info("Config Properties Loaded !!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}

			fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/OR.Properties");

			try {
				OR.load(fis);
				log.info("OR Properties Loaded !!!!");
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		if (config.getProperty("browser").equals("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/executables/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox browser Initiated !!!");
		} else if (config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/executables/chromedriver.exe");
			driver = new ChromeDriver();
			log.info("chrome driver initiallized");
		} else if (config.getProperty("browser").equals("ie")) {

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/test/resources/executables/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("IE browser Initiated !!!");
		}

		driver.get(config.getProperty("SiteUrl"));
		log.info("Navigated to the URL : " + config.getProperty("SiteUrl"));
		driver.manage().window().maximize();
		log.info("window maximized");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean IsElementPresent(By by) {

		try {
			driver.findElement(by);
			log.info("Navigated to add customer page $$$");
			return true;

		} catch (NoSuchElementException e) {

			log.info("add customer Button Not FOUND !!!!");
			return false;

		}

	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {

			driver.quit();

		}

		log.info("test case Execution Completed ###");
	}

}
