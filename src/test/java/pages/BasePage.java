package pages;

import config.constants.Globals;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    AppiumDriver driver;
    WebDriverWait wait;
    public BasePage(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(Globals.WAIT));
    }

    protected void pauseExecution(long waitTimeInMillis) {
        try {
            Thread.sleep(waitTimeInMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not pause execution of thread");
        }
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    protected WebElement waitForVisibility(By elementLocator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }

    protected void waitForClickability(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clear(WebElement element) {
        waitForVisibility(element);
        element.clear();
    }

    protected void tap(WebElement element) {
        waitForVisibility(element);
        element.click();
    }

    protected void tap(By elementLocator) {
        waitForVisibility(elementLocator).click();
    }

    protected void type(WebElement element, String text) {
        waitForVisibility(element);
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    protected String getAttribute(WebElement element, String attribute) {
        waitForVisibility(element);
        return element.getAttribute(attribute);
    }

    protected String getText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Element not found within set timeout");
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isEnabled();
        } catch (TimeoutException e) {
            System.out.println("Element not found within set timeout");
            return false;
        }
    }
}
