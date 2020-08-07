package com.tutorialsninja.selenium;

import static org.testng.Assert.assertTrue;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.RegisterPage;
import resources.Base;

public class RegistrationTest extends Base {
	public WebDriver driver;
	LandingPage lp;
	RegisterPage rp;
	AccountPage ap;
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@BeforeTest
	public void initialize() throws Exception {
		driver = initializeDriver();
		driver.get(props.getProperty("url"));
		lp = new LandingPage(driver);
		rp = new RegisterPage(driver);
		ap = new AccountPage(driver);
	}

	/**
	 * 
	 * This test goes to http://tutorialsninja.com/demo/index.php?route=common/home
	 * 
	 * Verify Registration process is working
	 * 
	 * 1. Click on My Account -> Register on Homepage 
	 * 2. Fill in the details and click on Continue button
	 * 3. Once on Account Success page, click on Continue button
	 * 4. Verify logged into account
	 */
	@Test
	public void testRegistration() {
		rp = lp.Register();
		ap = rp.completeRegister();
		
	}

}
