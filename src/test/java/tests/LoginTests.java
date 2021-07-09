package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page_objects.BurgerMenu;
import page_objects.HeaderPage;
import page_objects.LoginPage;
import resources.Base;

public class LoginTests extends Base{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(LoginTests.class.getName());
	LoginPage lpage;
	HeaderPage hpage;
	BurgerMenu burgerMenu;
	
	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void S1_loginValidation() throws IOException {
		lpage = new LoginPage(driver);
		hpage = new HeaderPage(driver);
		lpage.loginMethod();
		Assert.assertTrue(hpage.getLogo().isDisplayed());
		log.info("Successfully login");
	}
	
	@Test(priority=2)
	public void S2_invalidUserMessage() throws IOException {
		lpage = new LoginPage(driver);
		lpage.loginInvalidUser();
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", lpage.getErrorMessage().getText());
		log.info("Error Message is Displayed");
	}
	
	@Test(priority=3)
	public void S3_logoutValidation() throws IOException {
		lpage = new LoginPage(driver);
		lpage.loginMethod();
		lpage.logoutMethod();
		Assert.assertTrue(lpage.getLogin().isDisplayed());
		log.info("Logout successfully");
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
		driver=null;
	}
}
