package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CheckoutPage  {
	public WebDriver driver;
	public CommonObjects co;
	public LandingPage lp;
	
	@FindBy(css="input[name='payment_address'][value='new']")
	WebElement addressRadio;
	
	@FindBy(id="input-payment-firstname")
	WebElement pFirstname;
	
	@FindBy(id="input-payment-lastname")
	WebElement pLastname;
	
	@FindBy(id="input-payment-address-1")
	WebElement pAddress1;
	
	@FindBy(id="input-payment-city")
	WebElement pCity;
	
	@FindBy(id="input-payment-postcode")
	WebElement pPostcode;
	
	@FindBy(id="input-payment-country")
	WebElement selectC;
	@FindBy(id="input-payment-zone")
	WebElement selectZ;
	
	@FindBy(id="button-payment-address")
	WebElement continueBilling;
	
	@FindBy(name="address_id")
	WebElement selectA;
	
	
	
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		co = new CommonObjects(this.driver);
		// The initElements method will create all WebElements
				PageFactory.initElements(driver, this);
	}
	/**

     * This POM method will be exposed in test case to checkout items in cart

     */
	public void testCheckout() throws InterruptedException {
		addressRadio.click();
		pFirstname.click();
		pFirstname.sendKeys("Paul");
		pLastname.click();
		pLastname.sendKeys("Tal");
		pAddress1.click();
		pAddress1.sendKeys("16 Talbi st");
		pCity.click();
		pCity.sendKeys("Toronto");
		pPostcode.click();
		pPostcode.sendKeys("M1V 3E8");
		Select cDropdown = new Select(selectC);
		cDropdown.selectByVisibleText("Canada");
		Select zDropdown = new Select(selectZ);
		zDropdown.selectByVisibleText("Ontario");
		continueBilling.click();
		Thread.sleep(3000);
		Select aDropdown = new Select(selectA);
		aDropdown.getFirstSelectedOption();
		driver.findElement(By.cssSelector("#button-shipping-address")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#button-shipping-method")).click();	
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='checkbox'][name='agree']")).click();		
		driver.findElement(By.cssSelector("#button-payment-method")).click();	
		driver.findElement(By.cssSelector("input[value='Confirm Order']")).click();	
		Thread.sleep(3000);
		if(driver.getCurrentUrl().contains("/success")) {
			driver.findElement(By.cssSelector("a[href*='/home']")).click();		
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(true);
		}

	}
	
}
