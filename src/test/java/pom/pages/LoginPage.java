package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class LoginPage extends BasePage {
    //Locators
    private final By loginForm= By.cssSelector("#login");
    private final By cookieConsentButton= By.cssSelector("#cookie-consent-button");
    private final By loginUserNameField= By.cssSelector("#Username");
    private final By loginPasswordField= By.cssSelector("#Password");
    private final By submitButton= By.cssSelector("#MainLoginButton");
    private final By userNameValidationError= By.cssSelector("#Username-validation-error");
    private final By passwordValidationError= By.cssSelector("#Password-validation-error");


    //methods
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage acceptCookies() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cookieConsentButton)).click();
        return this;
    }
    public boolean VerifyLoginForm(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm)).isDisplayed();
    }
    public boolean VerifyUsernameField(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginUserNameField)).isDisplayed();
    }
    public boolean VerifyPasswordField(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPasswordField)).isDisplayed();
    }
    public boolean VerifySubmitButton(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).isDisplayed();
    }
    public LoginPage EnterUsername(String user){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginUserNameField)).sendKeys(user);
        return this;
    }
    public LoginPage EnterPassword(String pass){
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPasswordField)).sendKeys(pass);
        return this;
    }
    public HomePage Submit()
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();
        return new HomePage(driver);
    }
    public String getUsernameErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(userNameValidationError)).getText();
    }
    public String getPasswordErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordValidationError)).getText();
    }

}
