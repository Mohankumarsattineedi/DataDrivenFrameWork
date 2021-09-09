package com.acies.rough;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestRough {

	static Logger log = Logger.getLogger(TestRough.class);
	
	@Test(dataProvider="getData")
	public void test(String username, String passcode){
		
		System.out.println(username);
		System.out.println(passcode);
		
		
	}
	
	@DataProvider
	public Object[][] getData(){
		
		Object[][] data = new Object[10][10];
		
		data[0][0]="new";
		data[0][1]= "old";
		
		return data;
	}
	

}
