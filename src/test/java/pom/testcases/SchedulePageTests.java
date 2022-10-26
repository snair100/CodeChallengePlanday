package pom.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pom.Objects.Data;
import pom.base.BaseTest;
import pom.pages.LoginPage;
import pom.pages.SchedulePage;
import pom.utils.ReadJson;

import java.io.IOException;
import java.io.InputStream;

public class SchedulePageTests extends BaseTest {
    private Data data;
    @BeforeClass
    public void loadData() throws IOException {
        data=new Data();
        InputStream is=getClass().getClassLoader().getResourceAsStream("Data.json");
        data= ReadJson.deserializeJson(is,data);
    }
    @Test(description = "Verify that the schedule page url contains '/page/schedule' ")
    public void verifySchedulePageUrl() {
        LoginPage login=new LoginPage(driver);
        login.acceptCookies()
                .EnterUsername(data.getUsernameValid())
                .EnterPassword(data.getPasswordValid())
                .Submit()
                .navigateToSchedule();
        SchedulePage schedule=new SchedulePage(driver);
        Assert.assertTrue(schedule.verifySchedulePageUrl());
    }
    @Test (description = "Verify the employee count in schedule page")
    public void verifyEmployeeCount() {
        LoginPage login = new LoginPage(driver);
        login.acceptCookies()
                .EnterUsername(data.getUsernameValid())
                .EnterPassword(data.getPasswordValid())
                .Submit()
                .navigateToSchedule();
        SchedulePage schedule = new SchedulePage(driver);
        Assert.assertEquals(schedule.getCountOfEmployee(),Integer.parseInt(data.getCount()));
    }
    @Test(description = "Verify that the user can create a shift giving start and end time ")
    public void verifyShiftCreation()  {
        LoginPage login = new LoginPage(driver);
        login.acceptCookies()
                .EnterUsername(data.getUsernameValid())
                .EnterPassword(data.getPasswordValid())
                .Submit()
                .navigateToSchedule()
                .chooseCreateShift(data.getEmployee())
                .enterShiftDetails(data.getShiftStartTime(),data.getShiftEndTime())
                .createShift();
        SchedulePage schedule=new SchedulePage(driver);
        Assert.assertTrue(schedule.verifyAssignedShift(data.getEmployee()));
    }
}
