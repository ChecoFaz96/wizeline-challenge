package tests;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import commons.Utils;
import page_objects.CartPage;
import page_objects.HeaderPage;
import page_objects.LoginPage;
import resources.Base;

public class ItemTests extends Base{
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(LoginTests.class.getName());
	LoginPage lpage;
	HeaderPage hpage;
	CartPage cartPage;
	
	@BeforeMethod
	public void initialize() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@Test(priority=1)
	public void S4_sortProducts() throws IOException {
		lpage = new LoginPage(driver);
		lpage.loginMethod();
		hpage = new HeaderPage(driver);
		float[] arrayProducts = hpage.sortByPriceLow();
		Utils method = new Utils();
		float[] sortedArray = method.sortMethod(arrayProducts);
		Assert.assertTrue(Arrays.equals(arrayProducts, sortedArray));
	}
	
	@Test(priority=2)
	public void S5_validateMultipleItems() throws IOException {
		lpage = new LoginPage(driver);
		boolean flag;
		lpage.loginMethod();
		hpage = new HeaderPage(driver);
		flag =  hpage.validateProductsAddedCart();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3)
	public void S6_validateProduct() throws IOException {
		String product = "Sauce Labs Onesie";
		boolean flag;
		lpage = new LoginPage(driver);
		hpage = new HeaderPage(driver);
		lpage.loginMethod();
		flag = hpage.addProduct(product);
		Assert.assertTrue(flag); 
	}
	
	@Test(priority = 4)
	public void S7_completePurchase() throws IOException {
		lpage = new LoginPage(driver);
		lpage.loginMethod();
		cartPage = new CartPage(driver);
		String completeMessage;
		completeMessage = cartPage.purchaseProduct();
		Assert.assertEquals("THANK YOU FOR YOUR ORDER",completeMessage);
	}
	
	@AfterMethod
	public void teardown() {
		driver.close();
		driver=null;
	}

}
