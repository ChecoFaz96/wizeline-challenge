package page_objects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commons.Utils;

public class HeaderPage {
	public WebDriver driver;
	public HeaderPage(WebDriver driver) {
		this.driver=driver;
	}
	By logo = By.xpath("//div[@class='app_logo']");
	By burgerMenu = By.xpath("//button[@id='react-burger-menu-btn']");
	By sortContainer = By.xpath("//select[@class='product_sort_container']");
	By shoppingCart = By.xpath("//a[@class='shopping_cart_link']");
	
	public WebElement getLogo() {
		return driver.findElement(logo);
	}
	
	public WebElement getBurgerMenu() {
		return driver.findElement(burgerMenu);
	}
	
	public WebElement getSortContainer() {
		return driver.findElement(sortContainer);
	}
	
	public WebElement getShoppingCart() {
		return driver.findElement(shoppingCart);
	}
	
	public float[] sortByPriceLow() {
		WebDriverWait wait = new WebDriverWait(driver,20);
		HeaderPage hpage = new HeaderPage(driver);
		wait.until(ExpectedConditions.visibilityOf(hpage.getSortContainer()));
		hpage.getSortContainer().click();
		String clickonsortOption = Keys.chord(Keys.ARROW_DOWN, Keys.ARROW_DOWN,Keys.ENTER);
		hpage.getSortContainer().sendKeys(clickonsortOption);
		
		int numProducts = driver.findElements(By.xpath("//div[@class='inventory_item_price']")).size();
		float[] arrayProducts = new float[numProducts];
		
		for(int i=0; i < arrayProducts.length; i++) {
			String price = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])"+"["+(i+1)+"]")).getText().replace("$", ""); 
			float f = Float.parseFloat(price);
			arrayProducts[i]=f;
		}	
		return arrayProducts;
	}
	
	public boolean validateProductsAddedCart() {
		String[] nameProducts = new String[2];
		int j = 0;
		WebDriverWait wait = new WebDriverWait(driver,20);
		HeaderPage hpage = new HeaderPage(driver);
		wait.until(ExpectedConditions.visibilityOf(hpage.getBurgerMenu()));
		
		for(int i=0;i<2;i++) {	
			String product = driver.findElement(By.xpath("(//div[@class='inventory_item_name'])"+"["+(i+1)+"]")).getText();
			nameProducts[i] = product;
			driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])"+"["+(i+1)+"]")).click();
		}
		hpage.getShoppingCart().click();
		List listNames = (List) Arrays.asList(nameProducts);
		int numberProducts = driver.findElements(By.xpath("//div[@class='inventory_item_name']")).size();
		
		for(int i=0;i<numberProducts;i++) {
			String product = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
			if(listNames.contains(product)) {
				j++;
			}
		}	
		if(j==nameProducts.length) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addProduct(String productName) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		HeaderPage hpage = new HeaderPage(driver);
		wait.until(ExpectedConditions.visibilityOf(hpage.getBurgerMenu()));
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		for(int i=0; i < products.size();i++) {
			String nameProduct = products.get(i).getText();
			if(nameProduct.equals(productName)) {
				driver.findElements(By.xpath("//button[contains(text(),'Add to cart')]")).get(i).click();
				break;
			}
		}
		hpage.getShoppingCart().click();
		String productCart = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
		if(productCart.equals(productName)) {
			return true;
		}
		else {
			return false;
		}
	}
}
