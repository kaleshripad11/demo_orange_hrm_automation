package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page_Base {
    /**
     * Create a parameterized constructor taking webdriver as the parameter
     */
    WebDriver driver;
    public Page_Base(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
