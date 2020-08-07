package pageobjects;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	WebDriver driver;
	RegisterPage rp;
	LoginPage lop;
	CheckoutPage cp;
	CommonObjects co;
	/**
	 * 
	 * All WebElements are identified by @FindBy annotation
	 * 
	 */

//	WebElements for currency in NavBar
	@FindBy(xpath = "//span[text()='Currency']")
	WebElement currency;

// 	WebElement for Cart Button
	@FindBy(css = "span#cart-total")
	WebElement cartBtn;

//	WebElements for My Account
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;

//	WebElements for Register in My Account dropdown
	@FindBy(xpath = "//ul/li/a[text()='Register']")
	WebElement register;

//	WebElements for Login in My Account dropdown
	@FindBy(xpath = "//ul/li/a[text()='Login']")
	WebElement login;

// WebElement for Iphone Add To Cart 
	@FindBy(css = "div.row div.product-layout:nth-child(2) div div:nth-child(3) button span")
	WebElement iphone;

	// WebElement for Iphone Add To Cart
	@FindBy(css = "a[href*='/checkout']")
	WebElement checkout;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		co = new CommonObjects(this.driver);
		// The initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public WebElement getLogo() {
		return co.logo;
	}

	public void clickLogo() {
		co.logo.click();
	}

	public String getCartBtnText() {
		return cartBtn.getText();
	}
	/**

     * This POM method will be exposed in test case to get items in Dropdown for Currency

     * @return List<WebElement>

     */
	public List<WebElement> validateCurrencyElements() throws InterruptedException {
		List<WebElement> cDrop = driver.findElements(By.cssSelector("#form-currency div ul li"));
		return cDrop;

	}

	public void clickOnCurrency() {
		currency.click();
	}

	public RegisterPage Register() {
		myAccount.click();
		register.click();
		rp = new RegisterPage(driver);
		return rp;

	}

	public LoginPage getLoginPage() {
		myAccount.click();
		login.click();
		lop = new LoginPage(driver);
		return lop;
	}

	public void dropdownNotVisible() {
		if (!driver.findElement(By.cssSelector("ul.dropdown-menu")).isDisplayed()) {
			this.clickOnCurrency();
		} else {
			this.clickOnCurrency();
		}
	}

	public String getUrl() {

		return driver.getCurrentUrl();
	}
	/**

     * This POM method will be exposed in test case to clear cart if items are pending from last session

     */
	public void clearCart() throws InterruptedException {
		cartBtn.click();
		try {
			if (!(driver.findElement(By.cssSelector("p[text*='empty']")).isDisplayed())) {
				driver.findElement(By.cssSelector("button[title='Remove']")).click();
				Thread.sleep(3000);
			}
		} catch (Exception e) {
			// System.out.println("testing");
			driver.findElement(By.cssSelector("button[title='Remove']")).click();
		} finally {
			return;
		}
	}

	public CheckoutPage startCheckout() throws InterruptedException {
		iphone.click();
		Thread.sleep(3000);
		
		// verify cartbtnTxt contains 1 item
		if (co.successMsg.getText().contains("Success")) {
			//Thread.sleep(3000);
			// click on cart
			cartBtn.click();
			// click on checkout
			
			checkout.click();
			// return checkout page
			cp = new CheckoutPage(this.driver);
			return cp;
		} else {
			return null;
		}

	}
}
