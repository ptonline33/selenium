package com.tutorialsninja.selenium;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.CheckoutPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class CheckoutTest extends Base {
	public WebDriver driver;
	public Logger log;
	LandingPage lp;
	LoginPage lop;
	CheckoutPage cp;
	AccountPage ap;

	@BeforeTest
	public void initialize() throws Exception {
		driver = initializeDriver();
		driver.get(props.getProperty("url"));
		lp = new LandingPage(driver);
		cp = new CheckoutPage(driver);
		lop = new LoginPage(driver);
		ap = new AccountPage(driver);

	}

	/**
	 * 
	 * This test case will login into
	 * http://tutorialsninja.com/demo/index.php?route=common/home
	 * 
	 * Complete Checkout process End to End
	 * 
	 */
	@Test
	public void testCheckout() throws InterruptedException {
		// Naviagates to Login Page
		lp.getLoginPage();
		lop.loginToSite();
		// Go back to Landing Page
		lp = ap.goHome();
		// Clears Cart if items are pending from last session
		lp.clearCart();
		// Add item and get to checkout page
		cp = lp.startCheckout();
		// Complete Checkout Process
		cp.testCheckout();

	}

}
