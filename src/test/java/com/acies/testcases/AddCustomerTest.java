package com.acies.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.acies.base.TestBase;

public class AddCustomerTest extends TestBase {

	@Test(dataProvider = "addCustomer")
	public void addCustomer(String firstName, String lastName, String Postcode, String alertText)
			throws InterruptedException {

		log.info("trying to click on add Customer Button");
		driver.findElement(By.cssSelector(OR.getProperty("addcustomer"))).click();
		log.info("clicked on add customer button");

		driver.findElement(By.cssSelector(OR.getProperty("FirstName"))).sendKeys(firstName);
		log.info("sending the first Name" + firstName);
		driver.findElement(By.cssSelector(OR.getProperty("LastName"))).sendKeys(lastName);
		log.info("sending the last Name" + lastName);
		driver.findElement(By.cssSelector(OR.getProperty("Postcode"))).sendKeys(Postcode);
		log.info("sending the postcode Name" + Postcode);

		driver.findElement(By.cssSelector(OR.getProperty("addcustomBtn"))).click();

		
		// Handling alert
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		

	}

	@DataProvider(name = "addCustomer")
	public Object[][] getdata() throws Exception {

		String sheetName = "AddCustomerData";
		String excelfilePath = System.getProperty("user.dir") + "/src/test/resources/excel/testdata.xlsx";

		excel.setPath(excelfilePath, sheetName);
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColCount(sheetName);

		Object[][] data = new Object[rows][cols];

		for (int rowNum = 1; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				data[rowNum - 1][colNum] = excel.getCellData(rowNum, colNum);

			}

		}
		return data;

	}

}
