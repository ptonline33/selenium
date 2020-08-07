package pageobjects;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.Base;

public class CommonObjects {
	public WebDriver driver;
	// WebElement for logo
	@FindBy(css = "#logo h1 a")
	WebElement logo;

//	WebElement for error message while logging in
	@FindBy(css = "div.alert.alert-danger.alert-dismissible")
	WebElement errorMsg;
	
//	WebElement for success message 
	@FindBy(css = "div.alert.alert-success.alert-dismissible")
	WebElement successMsg;
	
//	WebElement for login btn
	@FindBy(css = "input[type='submit'][value='Continue']")
	WebElement continueSubmitType;

// 	WebElement for First Name 
	@FindBy(css = "#input-firstname")
	WebElement firstName;

// 	WebElement for Last Name 
	@FindBy(css = "input#input-lastname")
	WebElement lastName;

	public CommonObjects(WebDriver driver) {
		this.driver = driver;
		// The initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

}
