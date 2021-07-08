package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	 public WebDriver driver;
	    By checkoutBtn = By.xpath("//button[@id='checkout']");

	    public  CartPage(WebDriver driver) {
	        this.driver=driver;
	    }

	    public WebElement getCheckoutBtn() {
	        return this.driver.findElement(this.checkoutBtn);
	    }
	    
	    public String purchaseProduct() {
	    	WebDriverWait wait = new WebDriverWait(driver,20);
			HeaderPage hpage = new HeaderPage(driver);
			wait.until(ExpectedConditions.visibilityOf(hpage.getBurgerMenu()));
			driver.findElement(By.xpath("(//button[contains(text(),'Add to cart')])"+"["+(1)+"]")).click();
			hpage.getShoppingCart().click();
			CartPage cartPage = new CartPage(driver);
			cartPage.getCheckoutBtn().click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='first-name']"))));
			driver.findElement(By.xpath("//input[@id='first-name']")).click();
			driver.findElement(By.xpath("//input[@id='first-name']")).sendKeys("John");
			driver.findElement(By.xpath("//input[@id='last-name']")).click();
			driver.findElement(By.xpath("//input[@id='last-name']")).sendKeys("Miller");
			driver.findElement(By.xpath("//input[@id='postal-code']")).click();
			driver.findElement(By.xpath("//input[@id='postal-code']")).sendKeys("20000");
			driver.findElement(By.xpath("//input[@id='continue']")).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@id='finish']"))));
			driver.findElement(By.xpath("//button[@id='finish']")).click();
			String completeMessage = driver.findElement(By.xpath("//h2[@class='complete-header']")).getText();
			return completeMessage;
	    }
}
