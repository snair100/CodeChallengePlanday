package pom.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class HomePage extends BasePage {

    //Locators
    @FindBy(css = "div.styled__StyledHeaderTextContainer-home-dashboard__ujmh3d-7.fXdfCQ" )
    private WebElement homePageHeader;
    @FindBy(css = " nav.sc-ksdxgE.fqTjSF > ul > li:nth-child(2) > a" )
    private WebElement scheduleTab;

    //Methods
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public Boolean verifyHomepageHeader(){
        return wait.until(ExpectedConditions.visibilityOf(homePageHeader)).isDisplayed();
    }
    public SchedulePage navigateToSchedule(){
        wait.until(ExpectedConditions.visibilityOf(scheduleTab)).click();
        return new SchedulePage(driver);
    }

}
