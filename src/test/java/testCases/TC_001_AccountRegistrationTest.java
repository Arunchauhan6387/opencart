package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

	

	
	
	@Test(groups= {"Regression","Master"})
	public void VerifyAccountRegistration() {
		
		try {
		logger.info("*** Starting TC_001_AccountRegistrationTest ***");
		HomePage homepage = new HomePage(driver);
		homepage.ClickMyAccount();
		logger.info("Clicked on MyAccount link....");
		homepage.ClickOnRegister();
		logger.info("Clicked on Register link....");
		
		AccountRegistrationPage AccRegister = new AccountRegistrationPage(driver);
		logger.info("Providing customer details ....");
		
		AccRegister.setFirstname("arun");
		AccRegister.setLastname("chauhan");
		AccRegister.setEmail(randomString()+"@gmail.com");
		AccRegister.setTelephone("1234567890");
		
		String password = randomAlphaNumeric();
		AccRegister.setPassword(password);
		AccRegister.setConfirmPassword(password);
		AccRegister.ClickOnPrivecyPolicy();
		AccRegister.ClickOnContinue();
		
		logger.info("Validating Account Registration message....");
		String confmsg = AccRegister.ActConfirmatioMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}else {
			
			logger.error("Test failed");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}catch(Exception e) {
		
			Assert.fail();
			
		}
		
	}
	
	
}
