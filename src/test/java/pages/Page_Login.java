package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Login extends Page_Base{
    public Page_Login(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "input[name='username']") WebElement txtUser;
    @FindBy(css = "input[name='password']") WebElement txtPass;
    @FindBy(css = "button[type='submit']") WebElement btnLogin;
    @FindBy(xpath = "//p[normalize-space()='Invalid credentials']") public WebElement errorMessage;

    public void enterUserName(String user){
        txtUser.sendKeys(user);
    }

    public void enterPassword(String pass){
        txtPass.sendKeys(pass);
    }

    public void clickLoginBtn(){
        btnLogin.click();
    }
}
