package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pom.base.BasePage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class SchedulePage extends BasePage {
    //Locators
//    @FindBy(xpath = "//a[contains(text(),'Manage Employees')]" ) private WebElement link;
    @FindBy(css = "div[class='styled__StyledTextDiv-sc-y0eatq-3 kNFOfF']" )
    private WebElement employeeIcon;
    @FindBy(xpath = "div[class='edit-shift-modal__box edit-shift-modal__box--scroll']" )
    private WebElement shiftModal;
    @FindBy(id = "shiftStartEnd_start" )
    private WebElement shiftStartTime;
    @FindBy(id = "shiftStartEnd_end" )
    private WebElement shiftEndTime;
    @FindBy(css = "span[class='styled__StyledText-sc-1pogpev-0 bBOxDu sc-bnXvFD irlOeL']" )
    private WebElement employeeSelectFiled;
    @FindBy(xpath = "//span[@class='styled__StyledText-sc-1pogpev-0 bBOxDu sc-bnXvFD irlOeL' and contains(text(),'Employee One')]" )
    private WebElement selectEmployee;
    @FindBy(css = "li:nth-child(2) > button" )
    private WebElement createButton;

    //Methods
    public SchedulePage(WebDriver driver) {
        super(driver);
    }
    public Boolean verifySchedulePageUrl(){
       return wait.until(ExpectedConditions.urlContains("/schedule"));
    }
    public int getCountOfEmployee() {
        List<WebElement> employee= driver.findElements(By.cssSelector("div[class='styled__StyledTextDiv-sc-y0eatq-3 kNFOfF']")) ;
        return employee.size();
    }
    public SchedulePage chooseCreateShift(String employee)  {
        DateFormat dateFormat=new SimpleDateFormat("MMMM dd, YYYY");
        Date date=new Date();
        String text= dateFormat.format(date)+employee;
        String locator= "//div[contains(@aria-label,'"+text+"')]/div/div";
        wait.until(ExpectedConditions.visibilityOf(employeeIcon));
        WebElement e= driver.findElement(By.xpath(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", e);
        e.click();
        return this;
    }
    public SchedulePage enterShiftDetails(String startTime, String endTime){
        wait.until(ExpectedConditions.visibilityOf(shiftModal));
        shiftStartTime.sendKeys(startTime);
        shiftStartTime.sendKeys(endTime);
        employeeSelectFiled.click();
        selectEmployee.click();
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
