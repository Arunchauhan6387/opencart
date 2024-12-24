package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginPage extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void VerifyLogin() {
		
		
		logger.info("***Staring VerifyingLogin test**");
		
		try {
		HomePage homepage = new HomePage(driver);
		homepage.ClickMyAccount();
		homepage.ClickOnLogin();
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.setusername(p.getProperty("email"));
		loginpage.setpassword(p.getProperty("password"));
		loginpage.ClickOnLogin();
		
		MyAccountPage myaccountpage = new MyAccountPage(driver);
		 boolean myaccountmsg = myaccountpage.isMyAccountPageExist();
		Assert.assertTrue(myaccountmsg);
		
		
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***Finishing VerifyingLogin test**");
		
	}
}
