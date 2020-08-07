package com.tutorialsninja.selenium;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageobjects.LandingPage;
import resources.Base;

public class HomePageTests extends Base {
	WebDriver driver;
	LandingPage lp;
	public static Logger log;
	List<WebElement> cElements;
	String cText;

	@BeforeTest
	public void initialize() throws Exception {
		driver = initializeDriver();
		driver.get(props.getProperty("url"));
		log = LogManager.getLogger(Base.class.getName());
		log.info("Successfully navigated to Landing Page");
		lp = new LandingPage(driver);
	}

	/**
	 * 
	 * This test goes to http://tutorialsninja.com/demo/index.php?route=common/home
	 * 
	 * Verify Logo name is 'Our Store'
	 * 
	 * Click on Logo and verify if it returns to landing page
	 * 
	 * @throws InterruptedException
	 * 
	 * 
	 */
	@Test
	public void validateLogo() throws InterruptedException {
		Assert.assertEquals(lp.getLogo().getText(), "Your Store");
		log.info("Successfully validated logo name");
		lp.clickLogo();
		
		Assert.assertEquals(driver.getCurrentUrl(), props.getProperty("url"));
		log.info("Successfully validated url after clicking on logo");
	}

	/**
	 * 
	 * This test goes to http://tutorialsninja.com/demo/index.php?route=common/home
	 * 
	 * Verify Currency Element has Euro,Pound Sterling & US Dollar in dropdown
	 * option
	 */
	@Test
	public void validateCurrencyElements() throws InterruptedException {
		List<WebElement> cElements = new ArrayList<WebElement>();
		lp.dropdownNotVisible();
		cElements = lp.validateCurrencyElements();
		Assert.assertEquals(cElements.get(0).getText(), "€ Euro");
		Assert.assertEquals(cElements.get(1).getText(), "£ Pound Sterling");
		Assert.assertEquals(cElements.get(2).getText(), "$ US Dollar");
		log.info("All Elements verified in Currency Dropdown");
		lp.dropdownNotVisible();
	}

	/**
	 * 
	 * This test goes to http://tutorialsninja.com/demo/index.php?route=common/home
	 * 
	 * 
	 * Verify by clicking on currency element it changes the currency for the page
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void validateCurrencyChange() throws InterruptedException {
			cElements = new ArrayList<WebElement>();
			lp.clickOnCurrency();
			
			cElements = lp.validateCurrencyElements();
			System.out.println(cElements.size());
			for(int i=0;i<cElements.size();i++) {
				if(i>=1) {
					lp.dropdownNotVisible();
					cElements = lp.validateCurrencyElements();
				}
				cText = cElements.get(i).getText();
				cElements.get(i).click();
				
				switch (cText) {
				case "€ Euro":
					// check cart element for if text matches for Euro
					Assert.assertEquals(lp.getCartBtnText().contains("€"), true);
					log.info("Euro Currency is working");
					break;

				case "£ Pound Sterling":
					// check cart element for if text matches for Pound
					Assert.assertEquals(lp.getCartBtnText().contains("£"), true);
					log.info("Pound Sterling Currency is working");
					break;
				case "$ US Dollar":
					// check cart element for if text matches for US Dollar
					Assert.assertEquals(lp.getCartBtnText().contains("$"), true);
					log.info("US Dollar Currency is working");
					break;
				default:
					break;
				}
			}
			
		}
	}
