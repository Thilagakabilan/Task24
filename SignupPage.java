package pages;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage {

	@FindBy(id="signin2")
	WebElement SignIn;
	
	@FindBy(id="sign-username")
	WebElement SigninUserName;
	
	@FindBy(id="sign-password")
	WebElement SigninPassword;
	
	@FindBy(xpath="//*[contains(@onclick,'register()')]")
	WebElement SignintoPage;
	
	@FindBy(xpath="//*[@id='signInModal']/div/div/div[3]/button[1]")
	WebElement CloseSignintoPage;
	
	public void onClickSignUp() {
		SignIn.click();
	}
	
	public void sendInputSignUp(String username,String pass) {
		SigninUserName.sendKeys(username);
		SigninPassword.sendKeys(pass);
	}
	
	public void sendUserNameAlone(String username) {
		SigninUserName.sendKeys(username);
	}
	public void sendPasswordAlone(String password) {
		SigninUserName.sendKeys(password);
	}
	
	public void onClickSignintoPage() {
		SignintoPage.click();
	}
	
	public void onClickCloseSignintoPage() {
		CloseSignintoPage.click();
	}
	
	
	
}
