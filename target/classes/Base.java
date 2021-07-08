package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	public  static WebDriver driver;
	public Properties prop;
	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		
		prop.load(fis);
		//String browserName = System.getProperty("browser");
		String browserName =prop.getProperty("browser");
		
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
			driver = new ChromeDriver();			
		}
		else if(browserName.equals("firefox"))
		{
			//execute in firefox
			System.setProperty("webdriver.gecko.driver", "C:\\work\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		Date date = new Date();
		String str = "";
		str = dateFormat.format(date);
		str = str.replace(" ", "").replaceAll("/","").replaceAll(":","");
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+ " "+ str +".png";
		FileUtils.copyFile(src, new File (destinationFile));
		return destinationFile;
	}
}
