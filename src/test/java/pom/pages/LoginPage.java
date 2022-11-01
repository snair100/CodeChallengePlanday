package pom.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;
public class LoginPage extends BasePage {
    //Locators
    @FindBy(css = "#login" )
    private WebElement loginForm;
    @FindBy(css = "#cookie-consent-button" )
    private WebElement cookieConsentButton;
    @FindBy(css = "#Username" )
    private WebElement loginUserNameField;
    @FindBy(css = "#Password" )
    private WebElement loginPasswordField;
    @FindBy(css = "#MainLoginButton" )
    private WebElement submitButton;
    @FindBy(css = "#Username-validation-error")
    private WebElement userNameValidationError;
    @FindBy(css = "#Password-validation-error" )
    private WebElement passwordValidationError;


    //methods
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage acceptCookies() {
        wait.until(ExpectedConditions.visibilityOf(cookieConsentButton)).click();
        return this;
    }
    public boolean VerifyLoginForm(){
        return wait.until(ExpectedConditions.visibilityOf(loginForm)).isDisplayed();
    }
    public boolean VerifyUsernameField(){
        return wait.until(ExpectedConditions.visibilityOf(loginUserNameField)).isDisplayed();
    }
    public boolean VerifyPasswordField(){
        return wait.until(ExpectedConditions.visibilityOf(loginPasswordField)).isDisplayed();
    }
    public boolean VerifySubmitButton(){
        return wait.until(ExpectedConditions.visibilityOf(submitButton)).isDisplayed();
    }
    public LoginPage EnterUsername(String user){
        wait.until(ExpectedConditions.visibilityOf(loginUserNameField)).sendKeys(user);
        return this;
    }
    public LoginPage EnterPassword(String pass){
        loginPasswordField.sendKeys(pass);
        return this;
    }
    public HomePage Submit()
    {
        submitButton.click();
        return new HomePage(driver);
    }
    public String getUsernameErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(userNameValidationError)).getText();
    }
    public String getPasswordErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(passwordValidationError)).getText();
    }

}
