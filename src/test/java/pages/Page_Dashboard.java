package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page_Dashboard extends Page_Base {
    public Page_Dashboard(WebDriver driver){
        super(driver);
    }

    @FindBy(css = "p.oxd-userdropdown-name") public WebElement userLoginName;
    @FindBy(xpath = "//header/div/div[2]/ul/li/span/p") public WebElement dropdown_link;
    @FindBy(xpath = "//a[normalize-space()='Logout']") public WebElement logoutLink;

    public void clickUserLoginDropDown(){
        userLoginName.click();
    }

    public void clickLogOutLink(){
        logoutLink.click();
    }
}
