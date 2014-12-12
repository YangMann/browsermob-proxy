package com.microsoft.CAPS.util;

import com.microsoft.CAPS.conf.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by t-yaz on 12/11/2014.
 */
public class WebUICommonTestHelper {
    protected WebDriver driver;
    protected String baseURL;
    protected WebDriverWait waiter;
    protected int defaultWaitTime;

    public WebUICommonTestHelper(String baseURL, int defaultWaitTime, WebDriver driver) {
        this.driver = driver;
        this.baseURL = baseURL;
        this.defaultWaitTime = defaultWaitTime;
        this.waiter = new WebDriverWait(driver, defaultWaitTime);
    }

    public WebUICommonTestHelper(String baseURL, WebDriver driver) {
        this(baseURL, 31, driver);
    }

    public void login() {
        driver.navigate().to(this.baseURL);
        clear(By.id(ElementId.LoginPageEmail));
        sendKey(By.id(ElementId.LoginPageEmail), TestProperties.Account);
        clear(By.id(ElementId.LoginPagePassword));
        sendKey(By.id(ElementId.LoginPagePassword), TestProperties.Password);
        click(By.id(ElementId.LoginPageSignin));
    }

    public void clear(By locator) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
    }

    public void sendKey(By locator, String text) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }

    public void select(By locator, String text) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(driver.findElement(locator));
        select.selectByValue(text);

    }

    public void click(By locator) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(locator));
        waiter.until(ExpectedConditions.elementToBeClickable(locator));
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(locator)).click().perform();
    }

}
