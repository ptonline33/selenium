package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.Base;

public class LoginPage {
	WebDriver driver;
	public static Logger log;
	public AccountPage ap;
	public LandingPage lp;
	public CommonObjects co;

//	WebElement for entering in email to login
	@FindBy(id = "input-email")
	WebElement email;

//	WebElement for entering in password to login
	@FindBy(id = "input-password")
	WebElement password;

//	WebElement for login btn
	@FindBy(css = "input[type='submit'][value='Login']")
	WebElement loginBtn;
	
//	WebElement for error message while logging in
	@FindBy(css = "div.alert.alert-danger.alert-dismissible")
	WebElement errorMsg;
//	WebElement for success message 
	@FindBy(css = "div.alert.alert-success.alert-dismissible")
	WebElement successMsg;
	
//	WebElement for forgot password link
	@FindBy(css = "a[href*='/forgotten']")
	WebElement fPassword;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		log = LogManager.getLogger(Base.class.getName());
		ap = new AccountPage(this.driver);
		lp = new LandingPage(this.driver);
		co = new CommonObjects(this.driver);
		// The initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * This POM method will be exposed in test case to login into the application
	 */
	public AccountPage loginToSite() {
		email.click();
		email.sendKeys("pt@test.com");
		password.sendKeys("ind12");
		loginBtn.click();
		if (driver.getCurrentUrl().contains("/login")) {
			log.info(co.errorMsg.getText());
		} else if (driver.getCurrentUrl().contains("/account")) {
			log.info("You have logged in successfully");
		}
		return ap;
	}

	public void logoutFromSite() {
		if(driver.getCurrentUrl().contains("/account")) {
			lp = ap.performLogout();
		}
	}
	/**

     * This POM method will be exposed in test case to perform Forgot Password process.

     */
	public void performForgotPassword() {
		fPassword.click();
		email.sendKeys("pt@test.com");
		co.continueSubmitType.click();
		if(co.successMsg.isDisplayed()) {
			log.info(co.successMsg.getText());
		} else if(co.errorMsg.isDisplayed()) {
			log.error(co.errorMsg.getText());
		}
	}
}
