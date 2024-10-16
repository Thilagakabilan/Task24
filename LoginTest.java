package testcase;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class LoginTest {
	
	WebDriver driver;
	LoginPage login;
	
	@BeforeSuite
	public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	}
	
	@BeforeMethod
	public void beforeTestMethod() {
	driver.get("https://www.demoblaze.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test(priority=1)
	public void LoginWithValidUserNameAndPassword() throws Exception {
		login=PageFactory.initElements(driver, LoginPage.class);
		login.onClickLogin();
		login.sendInputLogin("123", "123");
		login.onClickLogintoPage();
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(20));	
		wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		String Expected=driver.findElement(By.id("nameofuser")).getText();
		String Actual="Welcome 123";
		Assert.assertEquals(Expected, Actual);
		screenshot();		
	}
	
	@Test(priority=2)
	public void logInWithInvalidUserNameAndPassword() throws Exception {
		login=PageFactory.initElements(driver, LoginPage.class);
		login.onClickLogin();
		login.sendInputLogin("hgfhfyg","ghfghfh");
		login.onClickLogintoPage();
		
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(20));	
		wt.until(ExpectedConditions.alertIsPresent());
		fullScreenScreenshot();
		String Actual=driver.switchTo().alert().getText();
		System.out.println(Actual);
		String Expected="User does not exist.";
		Assert.assertEquals(Expected, Actual);
		driver.switchTo().alert().accept();
		login.onClickCloseLogintoPage();
		
	}
	
	@Test(priority=3)
	public void logInWithValidUserNameAndInvalidPassword() throws Exception {
		login=PageFactory.initElements(driver, LoginPage.class);
		login.onClickLogin();
		login.sendInputLogin("123","589");
		login.onClickLogintoPage();
		
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(25));	
		wt.until(ExpectedConditions.alertIsPresent());
		fullScreenScreenshot();
		
		String Actual=driver.switchTo().alert().getText();
		System.out.println(Actual);
		String Expected="Wrong password.";
		Assert.assertEquals(Expected, Actual);
		driver.switchTo().alert().accept();
		login.onClickCloseLogintoPage();
	}
	
	@Test(priority=4)
	public void logInWithUserNameAndWithoutPassword() throws Exception {
		login=PageFactory.initElements(driver, LoginPage.class);
		login.onClickLogin();
		login.sendUserNameAloneLogin("123");
		login.onClickLogintoPage();
		
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(25));	
		wt.until(ExpectedConditions.alertIsPresent());
		fullScreenScreenshot();
		
		String Actual=driver.switchTo().alert().getText();
		System.out.println(Actual);
		String Expected="Please fill out Username and Password.";
		Assert.assertEquals(Expected, Actual);
		driver.switchTo().alert().accept();
		login.onClickCloseLogintoPage();
	}
	
	
	@AfterMethod
	public void afterTestMethod() {
		
	}
	
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
	
	public void screenshot() throws Exception {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		Date d=new Date();
		String filename=d.toString().replace(":","_").replace(" ", "_") +".png";
		File tgt=new File(".\\screenshots\\"+filename);
		FileUtils.copyFile(src,tgt);
	}
	
	public void fullScreenScreenshot() throws Exception  {
		
		Robot robotClassObject = new Robot();
		Thread.sleep(15);
		Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage tmp = robotClassObject.createScreenCapture(screenSize); 
		Date d=new Date();
		String path=d.toString().replace(":","_").replace(" ", "_") +".png";
		ImageIO.write(tmp, "png",new File(".\\screenshots\\"+path)); 
	}

}
