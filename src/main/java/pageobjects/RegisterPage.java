package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import resources.Base;

public class RegisterPage {
	WebDriver driver;
	public static Logger log;
	public CommonObjects co;
	public AccountPage ap;


// 	WebElement for email Label
	@FindBy(css = "#input-email")
	WebElement email;

// 	WebElement for phone label
	@FindBy(css = "#input-telephone")
	WebElement phone;

// 	WebElement for password label
	@FindBy(css = "#input-password")
	WebElement password;

// 	WebElement for confirm password label
	@FindBy(css = "#input-confirm")
	WebElement confirm;

// 	WebElement for subscribe radio button
	@FindBy(css = "input[name='newsletter'][value='0']")
	WebElement subscribe;

// 	WebElement for privacy checkbox
	@FindBy(css = "input[name='agree'][value='1']")
	WebElement privacyCheckbox;


// 	WebElement for Continue Button on Account Success Page
	@FindBy(css = "a[innertext='Continue']")
	WebElement continueBtn2;


	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		log = LogManager.getLogger(Base.class.getName());
		ap = new AccountPage(this.driver);
		co = new CommonObjects(this.driver);
		// The initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	/**

     * This POM method will be exposed in test case to login in the application

     * @return AccountPage

     */
	public AccountPage completeRegister() {
		co.firstName.click();
		co.firstName.sendKeys("Paul");
		co.lastName.click();
		co.lastName.sendKeys("Tal");
		email.click();
		email.sendKeys("test@test.com");
		phone.click();
		phone.sendKeys("6453214532");
		password.click();
		password.sendKeys("ind12");
		confirm.click();
		confirm.sendKeys("ind12");
		subscribe.click();
		privacyCheckbox.click();
		co.continueSubmitType.click();
		if (driver.getCurrentUrl().contains("success")) {
			continueBtn2.click();
			if (driver.getCurrentUrl().contains("route=account/account")) {
				log.info("You are registered and logged in successfully");
				return ap;
			} else {
				log.error("You are not registered");
				Assert.assertFalse(true);
				return null;
			}
		} else {
			log.error(co.errorMsg.getText());
			Assert.assertTrue(true);
			return null;
			
		}
	}

}
