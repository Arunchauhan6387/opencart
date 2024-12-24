package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Basepage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement InkMyAccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement InkRegister;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement InkLogin;
	
	
	
	public void ClickMyAccount() {
		InkMyAccount.click();
	}
	
	public void ClickOnRegister() {
		InkRegister.click();
	}
	
	public void ClickOnLogin() {
		InkLogin.click();
	}
	
}
