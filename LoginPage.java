package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	@FindBy(id="login2")
	WebElement LogIn;
	
	@FindBy(id="loginusername")
	WebElement LoginUserName;
	
	@FindBy(id="loginpassword")
	WebElement LoginPassword;
	
	@FindBy(xpath="//*[contains(@onclick,'logIn()')]")
	WebElement LogintoPage;
	
	@FindBy(xpath="//*[@id=\"logInModal\"]/div/div/div[3]/button[1]")
	WebElement CloseLogintoPage;
	
	public void onClickLogin() {
		LogIn.click();
	}
	
	public void sendInputLogin(String username,String pass) {
		LoginUserName.sendKeys(username);
		LoginPassword.sendKeys(pass);
	}
	
	public void sendUserNameAloneLogin(String username) {
		LoginUserName.sendKeys(username);
	}
	public void sendPasswordAloneLogin(String password) {
		LoginUserName.sendKeys(password);
	}
	
	public void onClickLogintoPage() {
		LogintoPage.click();
	}
	
	public void onClickCloseLogintoPage() {
		CloseLogintoPage.click();
	}
	
	
	
}

