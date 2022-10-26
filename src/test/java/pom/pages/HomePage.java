package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;

public class HomePage extends BasePage {

    //Locators
    private final By homePageHeader= By.cssSelector("div.styled__StyledHeaderTextContainer-home-dashboard__ujmh3d-7.fXdfCQ");
    private final By scheduleTab= By.cssSelector(" nav.sc-ksdxgE.fqTjSF > ul > li:nth-child(2) > a");
    //Methods
    public HomePage(WebDriver driver) {
        super(driver);
    }
    public Boolean verifyHomepageHeader(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(homePageHeader)).isDisplayed();
    }
    public SchedulePage navigateToSchedule(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(scheduleTab)).click();
        return new SchedulePage(driver);
    }

}
