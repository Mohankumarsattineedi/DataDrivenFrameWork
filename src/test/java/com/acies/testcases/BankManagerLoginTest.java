package com.acies.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.acies.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	
	
	@Test
	public void LoginAsBankManager() throws InterruptedException{
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		log.info("trying to click on  Login as Manager Button");
		driver.findElement(By.cssSelector(OR.getProperty("BMLogin"))).click();
		log.info("login Successful as Bank Manager");
		Assert.assertTrue(IsElementPresent(By.cssSelector(OR.getProperty("addcustomer"))),"Login Not Successful !!!");
		Reporter.log("Login Successful as Bank Manager");
		Reporter.log("<a targrt=\"_blank\" href=\"C:\\Users\\pavan\\Desktop\\console.JPG\" >Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a targrt=\"_blank\" href=\"C:\\Users\\pavan\\Desktop\\console.JPG\" <img src=\"\"></img> >Screenshot</a>");

		Thread.sleep(5000);
	}
	
	
	
}
