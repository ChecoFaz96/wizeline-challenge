package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BurgerMenu {
	public WebDriver driver;
	
	public BurgerMenu(WebDriver driver) {
		this.driver=driver;
	}

	By logoutOption = By.xpath("//a[@id='logout_sidebar_link']");
	
	public WebElement getLogoutOption() {
		return driver.findElement(logoutOption);
	}
}
