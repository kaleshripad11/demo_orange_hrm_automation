package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page_Home extends Page_Base {
    public Page_Home(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//h6[contains(@class,'header')]") public WebElement actualHeaderText;
}
