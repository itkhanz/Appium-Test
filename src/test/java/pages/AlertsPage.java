package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.interfaces.PopupHandling;

import java.time.Duration;
import java.util.List;

public class AlertsPage extends BasePage implements PopupHandling {
    private static Logger logger = LoggerFactory.getLogger(AlertsPage.class);
    public AlertsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    /*
    Assuming this screen has unexpected popups occurring automatically
    so we handle this popup automatically before performing every action on this screen
     */
    @Override
    public void handleUnexpectedPopup() {
        String popUpText = "Lorem ipsum dolor sit aie consectetur adipiscing";
        By popUp = AppiumBy.xpath("//android.widget.TextView[contains(@text, '" + popUpText + "')]");
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeForPopup));
        try {
            customWait.until(ExpectedConditions.visibilityOfElementLocated(popUp));
            logger.info("Unexpected popup found");
            By okBtn = AppiumBy.id("android:id/button1");
            tap(okBtn);
            logger.info("Unexpected popup closed with Ok");
        } catch (TimeoutException e) {
            assert true;
        }
    }

    @AndroidFindBy(accessibility = "OK Cancel dialog with a message")
    private WebElement okCancelDialog;

    @AndroidFindBy(accessibility = "List dialog")
    private WebElement listDialog;

    @AndroidFindBy(id = "android:id/alertTitle")
    private WebElement headerTitle;

    public void openOkCancelDialog() {
        handleUnexpectedPopup();
        logger.info("tapping on 'OK Cancel dialog with a message'");
        tap(okCancelDialog);
    }

    public void openListDialog() {
        handleUnexpectedPopup();
        logger.info("tapping on 'List Dialog'");
        tap(listDialog);
    }

    public boolean isHeaderTitleInListDialogDisplayed() {
        handleUnexpectedPopup();
        logger.info("checking if 'List Dialog' popup is displayed");
        return isElementDisplayed(headerTitle);
    }
}
