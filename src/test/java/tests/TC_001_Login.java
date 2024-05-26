package tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.Page_Dashboard;
import pages.Page_Home;
import pages.Page_Login;
import testbase.Test_Base;

public class TC_001_Login extends Test_Base {
    public Page_Login login;
    public Page_Home home;

    @Test(priority = 0)
    void testLoginWithValidCredentials(){
        log.info("***** Starting Test *****");
        login = new Page_Login(driver);
        home = new Page_Home(driver);
        login.enterUserName(rb.getString("username"));
        login.enterPassword(rb.getString("password"));
        login.clickLoginBtn();
        String expHeader = "Dashboard";
        Assert.assertEquals(home.actualHeaderText.getText().toString(), expHeader);
        log.info("***** Finished Test *****");
    }

    @Test(dependsOnMethods = {"testLoginWithValidCredentials"})
    public void logout() {
        Page_Dashboard dashboard = new Page_Dashboard(driver);
        log.info("***** Logging out of the application *****");
        wait.until(ExpectedConditions.elementToBeClickable(dashboard.dropdown_link)).click();
        wait.until(ExpectedConditions.elementToBeClickable(dashboard.logoutLink)).click();
        //dashboard.clickUserLoginDropDown();
        log.info("***** Logged out successfully *****");
    }

    @Test(priority = 1)
    void testLoginWithInValidUserNameValidPassword(){
        log.info("***** Starting Test *****");
        login.enterUserName(rb.getString("invalidUserName"));
        login.enterPassword(rb.getString("password"));
        login.clickLoginBtn();
        String expError = "Invalid credentials";
        Assert.assertEquals(login.errorMessage.getText().toString(), expError);
        log.info("***** Finished Test *****");
    }

    @Test(priority = 2)
    void testLoginWithInValidUserNameInvalidPassword(){
        log.info("***** Starting Test *****");
        login.enterUserName(rb.getString("invalidUserName"));
        login.enterPassword(rb.getString("invalidPassword"));
        login.clickLoginBtn();
        String expError = "Invalid credentials";
        Assert.assertEquals(login.errorMessage.getText().toString(), expError);
        log.info("***** Finished Test *****");
    }

    @Test(priority = 3)
    void testLoginWithValidUserNameInvalidPassword(){
        log.info("***** Starting Test *****");
        login.enterUserName(rb.getString("username"));
        login.enterPassword(rb.getString("invalidPassword"));
        login.clickLoginBtn();
        String expError = "Invalid credentials";
        Assert.assertEquals(login.errorMessage.getText().toString(), expError);
        log.info("***** Finished Test *****");
    }

    @Test(priority = 4)
    void testLoginWithBlankUserNameBlankPassword(){
        log.info("***** Starting Test *****");
        login.enterUserName("");
        login.enterPassword("");
        login.clickLoginBtn();
        String expError = "Invalid credentials";
        Assert.assertEquals(login.errorMessage.getText().toString(), expError);
        log.info("***** Finished Test *****");
    }
}
