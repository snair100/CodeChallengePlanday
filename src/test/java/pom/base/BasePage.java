package pom.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pom.Objects.Data;
import pom.utils.ReadJson;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait longWait;
    public BasePage(WebDriver driver) {
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        longWait=new WebDriverWait(driver,Duration.ofSeconds(50));
    }
}
