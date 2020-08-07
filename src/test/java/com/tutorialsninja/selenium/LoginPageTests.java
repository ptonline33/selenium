package com.tutorialsninja.selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import pageobjects.RegisterPage;
import resources.Base;

public class LoginPageTests extends Base {
	public WebDriver driver;
	LandingPage lp;
	LoginPage lop;
	AccountPage ap;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	@BeforeTest
	public void initialize() throws Exception {
		driver = initializeDriver();
		driver.get(props.getProperty("url"));
		lp = new LandingPage(driver);
		lop = new LoginPage(driver);
		ap = new AccountPage(driver);
	}

	/**
	 * 
	 * This test goes to http://tutorialsninja.com/demo/index.php?route=common/home
	 * 
	 * Verify Login process is working
	 * 
	 * 1. Click on My Account -> Login on Homepage 2. Fill in email address,password
	 * and click Login 4. Verify logged into account
	 */
	@Test
	public void testLogin() {
		lop = lp.getLoginPage();
		ap = lop.loginToSite();
		if (ap.getUrl().contains("/account")) {
			log.info("You are successfully on the account page");
		}

	}

	/**
	 * Verify Forgot Password function is working as intended
	 */
	@Test
	public void forgotPasswordTest() {
		lop = lp.getLoginPage();
		lop.performForgotPassword();

	}

	/**
	 * Verify user is able to logout from application
	 */
	@Test
	public void testLogout() {
		if (lp.getUrl().contains("/account")) {
			lp = ap.performLogout();
		} else {
			this.testLogin();
			lp = ap.performLogout();
		}
		if (lp.getUrl().contains("/home")) {
			log.info("You have successfully logged out and on the homepage");
		} else {
			log.error("Log out Failure");
		}

	}
}
