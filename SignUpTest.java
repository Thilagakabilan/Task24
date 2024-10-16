package testcase;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.SignupPage;

public class SignUpTest {

	WebDriver driver;
	SignupPage signup;
	LoginTest logintest=new LoginTest();
	
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
	public void signUpWithValidUserNameAndPassword() throws Exception {
		signup=PageFactory.initElements(driver, SignupPage.class);
		signup.onClickSignUp();
		signup.sendInputSignUp("user11", "123");
		signup.onClickSignintoPage();
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(10));	
		logintest.fullScreenScreenshot();
		wt.until(ExpectedConditions.alertIsPresent()).accept();
		signup.onClickCloseSignintoPage();
	}
	
	@Test(priority=2)
	public void signUpWithUserNameAndWithoutPassword() throws Exception {
		signup=PageFactory.initElements(driver, SignupPage.class);
		signup.onClickSignUp();
		signup.sendUserNameAlone("user12");
		signup.onClickSignintoPage();
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(10));	
		logintest.fullScreenScreenshot();
		wt.until(ExpectedConditions.alertIsPresent()).accept();
		signup.onClickCloseSignintoPage();
	}
	
	@Test(priority=3)
	public void signUpWithoutUserNameAndWithPassword() throws Exception {
		signup=PageFactory.initElements(driver, SignupPage.class);
		signup.onClickSignUp();
		signup.sendPasswordAlone("235");
		signup.onClickSignintoPage();
		WebDriverWait wt=new WebDriverWait(driver,Duration.ofSeconds(10));	
		logintest.fullScreenScreenshot();
		
		wt.until(ExpectedConditions.alertIsPresent()).accept();
		signup.onClickCloseSignintoPage();
	}
	
	@AfterMethod
	public void afterTestMethod() {
		
	}
	
	@AfterSuite
	public void afterSuite() {
		driver.quit();
	}
	
	
	
}
