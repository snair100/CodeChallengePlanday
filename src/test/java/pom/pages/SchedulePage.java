package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import pom.base.BasePage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SchedulePage extends BasePage {
    //Locators
    private final By link=By.xpath("//a[contains(text(),'Manage Employees')]");
    private final By employeeIcon= By.cssSelector("div[class='styled__StyledTextDiv-sc-y0eatq-3 kNFOfF']");
    private final By shiftModal=By.cssSelector("div[class='edit-shift-modal__box edit-shift-modal__box--scroll']");
    private final By shiftStartTime=By.id("shiftStartEnd_start");
    private final By shiftEndTime=By.id("shiftStartEnd_end");
    private final By employeeSelectFiled=By.cssSelector("span[class='styled__StyledText-sc-1pogpev-0 bBOxDu sc-bnXvFD irlOeL']");
    private final By selectEmployee=By.xpath("//span[@class='styled__StyledText-sc-1pogpev-0 bBOxDu sc-bnXvFD irlOeL' and contains(text(),'Employee One')]");
    private final By createButton=By.cssSelector("li:nth-child(2) > button");

    //Methods
    public SchedulePage(WebDriver driver) {
        super(driver);
    }
    public Boolean verifySchedulePageUrl(){
       return wait.until(ExpectedConditions.urlContains("/schedule"));
    }
    public int getCountOfEmployee() {
         return driver.findElements(employeeIcon).size();
    }
    public SchedulePage chooseCreateShift(String employee)  {
        DateFormat dateFormat=new SimpleDateFormat("MMMM dd, YYYY");
        Date date=new Date();
        String text= dateFormat.format(date)+employee;
        String locator= "//div[contains(@aria-label,'"+text+"')]/div/div";
        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIcon));
        WebElement e= driver.findElement(By.xpath(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    public SchedulePage enterShiftDetails(String startTime, String endTime){
        wait.until(ExpectedConditions.visibilityOfElementLocated(shiftModal));
        wait.until(ExpectedConditions.visibilityOfElementLocated(shiftStartTime)).sendKeys(startTime);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shiftStartTime)).sendKeys(endTime);
        driver.findElement(employeeSelectFiled).click();
        driver.findElement(selectEmployee).click();
        return this;
    }
    public SchedulePage createShift()
    {
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
        return this;
    }
    public Boolean verifyAssignedShift(String employee){
        String locator="//div[@class='row-header3__text__title' and contains(text(),'"+employee+"')]/ancestor::div[@class='virtualized-board__row']//div[@class='shift-tile shift-tile--editable shift-tile--assigned']";
        By shiftAssigned= By.xpath(locator);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(shiftAssigned)).isDisplayed();
    }

}
