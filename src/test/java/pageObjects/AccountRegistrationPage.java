package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends Basepage{

	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstNameField;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastNameField;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmailField;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephoneField;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwdField;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirrmPwdField;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement ChkPrivecyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement BtnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement MsgAccountConfirmation;
	
	
	public void setFirstname(String Fname)
	
	{
		txtFirstNameField.sendKeys(Fname);
	}
	
	public void setLastname(String Lname)
	{
		txtLastNameField.sendKeys(Lname);
	}
	public void setEmail(String email)
	{
		txtEmailField.sendKeys(email);
	}
	public void setTelephone(String tel)
	{
		txtTelephoneField.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPwdField.sendKeys(pwd);
	}
	public void setConfirmPassword(String pwd)
	{
		txtConfirrmPwdField.sendKeys(pwd);
	}
	
	public void ClickOnPrivecyPolicy()
	{
		ChkPrivecyPolicyField.click();
	}
	public void ClickOnContinue()
	{
		BtnContinue.click();
	}
	
	public String ActConfirmatioMsg() {
		try{
			return (MsgAccountConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
	
}
