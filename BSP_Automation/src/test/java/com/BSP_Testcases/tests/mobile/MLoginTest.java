package com.BSP_Testcases.tests.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.BSP_Testcases.core.BaseConfiguration;
import com.BSP_Testcases.page.actions.mobile.MHomePage;
import com.BSP_Testcases.page.actions.mobile.MLoginPage;
import com.BSP_Testcases.page.actions.mobile.MMyAccountPage;
import com.BSP_Testcases.page.validators.mobile.MLoginValidator;
import com.BSP_Testcases.utils.common.Config;
import com.BSP_Testcases.utils.selenium.WebUtils;

public class MLoginTest extends BaseConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(MLoginTest.class);

	private MLoginPage loginACtions;
	private MHomePage homePageActions;
	private MMyAccountPage myAccountPage;
	private MLoginValidator loginValidator;
	
	@BeforeClass
	public void setUp() {
		loginACtions = new MLoginPage(driver);
		homePageActions = new MHomePage(driver);
		myAccountPage = new MMyAccountPage(driver);
		loginValidator = new MLoginValidator();
	}
	
	@Test (testName = "TC_01", description = "To validate Successful B2B login")
	public void validate_b2b_login() {
		homePageActions.openLoginPage();
		loginACtions.loginWithDefaultCreds();
		String contactInfoOnPage = myAccountPage.getContactInfo();
		loginValidator.validateSuccessLogin(contactInfoOnPage, Config.getProperty("username"));
		WebUtils.sleep(1);
	}
	
	@Test (testName = "TC_02", description = "To validate successfull dealer login.")
	public void validate_dealer_login() {
		
		String dealerUserName = Config.getProperty("dealerUsername");
		
		homePageActions.openDealerLoginPage();
		loginACtions.login(dealerUserName, Config.getProperty("dealerPassword"));
		String contactInfoOnPage = myAccountPage.getContactInfo();
		loginValidator.validateSuccessLogin(contactInfoOnPage, dealerUserName);
		WebUtils.sleep(1);
	}

}
