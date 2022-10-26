package pom.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.Objects.Data;
import pom.base.BaseTest;
import pom.pages.HomePage;
import pom.pages.LoginPage;
import pom.utils.ReadJson;
import java.io.IOException;
import java.io.InputStream;

public class LoginTests extends BaseTest {
    Data data;
    @BeforeClass
    public void loadData() throws IOException {
        data=new Data();
        InputStream is=getClass().getClassLoader().getResourceAsStream("Data.json");
        data= ReadJson.deserializeJson(is,data);
    }

    @Test(description = "Verify that the login page has a login form username field, password field and submit button")
    public void verifyLoginForm() {
        LoginPage login=new LoginPage(driver);
        login.acceptCookies();
        Assert.assertTrue(login.VerifyLoginForm());
        Assert.assertTrue(login.VerifyUsernameField());
        Assert.assertTrue(login.VerifyPasswordField());
        Assert.assertTrue(login.VerifySubmitButton());
    }
    @Test(description = "Verify that the error messages are as expected for invalid username and password")
    public void verifyInvalidLogin() {
        LoginPage login=new LoginPage(driver);
        login.acceptCookies()
                .EnterUsername(data.getUsernameInvalid())
                .EnterPassword(data.getPasswordInvalid())
                .Submit();
        Assert.assertEquals(data.getUsernameValidationError(),login.getUsernameErrorMessage(),"Verified that the validation error is as expected");
        Assert.assertEquals(data.getPasswordValidationError(),login.getPasswordErrorMessage(),"Verified that the validation error is as expected");

    }
    @Test (description = "Verify that a successful login lands on HomePage")
    public void verifyValidLogin() {
        LoginPage login=new LoginPage(driver);
        login.acceptCookies()
                .EnterUsername(data.getUsernameValid())
                .EnterPassword(data.getPasswordValid())
                .Submit();
        HomePage home=new HomePage(driver);
        Assert.assertTrue(home.verifyHomepageHeader());
    }
}
