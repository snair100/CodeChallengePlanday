package pom.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pom.Objects.Data;
import pom.factory.DriverManager;
import pom.utils.ReadJson;

import java.io.IOException;
import java.io.InputStream;

public class BaseTest {
    protected WebDriver driver;
    @BeforeMethod
    public void startDriver() throws IOException {
        driver= new DriverManager().initializeDriver();
    }
    @AfterMethod
    public void quitDriver(){
        driver.quit();
    }
}
