package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;




public class TC_003_LoginDataDrivenTest extends BaseClass{


	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups= ("DataDriver"))
	public void verify_loginDDT(String email,String pwd, String exp) {
		
	logger.info("*****Starting TC_003_LoginDataDrivenTest*****");
	//Homepage
	
	try {
	HomePage homepage = new HomePage(driver);
	homepage.ClickMyAccount();
	homepage.ClickOnLogin();
	
	//loginpage
	
	LoginPage loginpage = new LoginPage(driver);
	loginpage.setusername(email);
	loginpage.setpassword(pwd);
	loginpage.ClickOnLogin();
	
	//myaccount
	MyAccountPage myaccountpage = new MyAccountPage(driver);
	boolean myaccountmsg = myaccountpage.isMyAccountPageExist();
	/*
	 
	 valid - login success- test pass - logout
	 	   - login failed - test fail
	 
	 invalid - login success - test fail - logout
	 		 - login fail - test pass 
	 
	 */
	 
	if(exp.equalsIgnoreCase("Valid")) {
		if(myaccountmsg==true)
		{
			
			myaccountpage.clickOnlogout();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(myaccountmsg==true) 
		{
			myaccountpage.clickOnlogout();
			Assert.assertTrue(false);
		}
		else {
			Assert.assertTrue(true);
		}
	}}
	catch(Exception e) {
		Assert.fail();
	}
	
	logger.info("*****Starting TC_003_LoginDataDrivenTest*****");
	
	 
	}
}
