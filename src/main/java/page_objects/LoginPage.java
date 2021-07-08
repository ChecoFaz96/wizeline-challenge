package page_objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
	public WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver= driver;
	}
	By username = By.xpath("//input[@id='user-name']");
	By password = By.xpath("//input[@id='password']");
	By loginBtn = By.xpath("//input[@id='login-button']");
	By errorMessage = By.xpath("//h3[@data-test='error']");
	
	public WebElement getUsername() {
		return driver.findElement(username);
	}
	
	public WebElement getPassword() {
		return driver.findElement(password);
	}
	
	public WebElement getLogin() {
		return driver.findElement(loginBtn);
	}
	
	public WebElement getErrorMessage() {
		return driver.findElement(errorMessage);
	}
	
	public void loginMethod() throws IOException {
		Properties prop;
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		LoginPage lpage = new LoginPage(driver);
		lpage.getUsername().click();
		lpage.getUsername().sendKeys(prop.getProperty("username"));
		lpage.getPassword().click();
		lpage.getPassword().sendKeys(prop.getProperty("password"));
		lpage.getLogin().click();
		WebDriverWait wait = new WebDriverWait(driver,20);
		HeaderPage hpage = new HeaderPage(driver);
		wait.until(ExpectedConditions.visibilityOf(hpage.getLogo()));
	}
	
	public void loginInvalidUser() throws IOException {
		Properties prop;
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		LoginPage lpage = new LoginPage(driver);
		lpage.getUsername().click();
		lpage.getUsername().sendKeys("test@mail.com");
		lpage.getPassword().click();
		lpage.getPassword().sendKeys(prop.getProperty("password"));
		lpage.getLogin().click();
	}
	
	public void logoutMethod() {
		HeaderPage hpage = new HeaderPage(driver);
		BurgerMenu burgerMenu;
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOf(hpage.getBurgerMenu()));
		hpage.getBurgerMenu().click();
		burgerMenu = new BurgerMenu(driver);
		wait.until(ExpectedConditions.visibilityOf(burgerMenu.getLogoutOption()));
		burgerMenu.getLogoutOption().click();
	}

}
