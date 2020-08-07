package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.Base;

public class AccountPage {
	WebDriver driver;
	public static Logger log;
	LandingPage lp;
	public CommonObjects co;

//	WebElements for My Account
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;

	@FindBy(css = "a[href*='/logout']:nth-child(1)")
	WebElement logoutBtn;
	@FindBy(css = "a[href*='/home']")
	WebElement continueBtn;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		log = LogManager.getLogger(Base.class.getName());
		lp = new LandingPage(this.driver);
		co = new CommonObjects(this.driver);
		// The initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	/**
	 * 
	 * This POM method will be exposed in test case to logout from the application
	 * 
	 * @return LandingPage
	 * 
	 */
	public LandingPage performLogout() {
		myAccount.click();
		logoutBtn.click();
		if (driver.getCurrentUrl().contains("/logout")) {
			continueBtn.click();
		}
		return lp;
	}

	public String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * 
	 * This POM method will be exposed in test case to get back to landing page
	 * 
	 * @return LandingPage
	 * 
	 */
	public LandingPage goHome() {
		co.logo.click();
		return lp;
	}

}
