package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Basepage{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtuname;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	@FindBy(xpath="//input[@value='Login']")
	WebElement InkLogin;
	
	public void setusername(String uname)
	{
		txtuname.sendKeys(uname);
	}
	
	public void setpassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	public void ClickOnLogin() {
		InkLogin.click();
	}
	
	

}
