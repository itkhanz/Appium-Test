package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ViewsPage extends BasePage{
    private static Logger logger = LoggerFactory.getLogger(ViewsPage.class);
    public ViewsPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "Spinner")
    private WebElement spinnerLO;

    private final By spinnerLOLocator = AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Spinner\"))"
    );

    /*
    This method automatically scrolls to the Spinner because of UiScrollable interface of UiAutomator2
    Alternatively you can also scroll to element via GestureUtils W3C Sequences or built-in driver commands
     */
    public SpinnerPage openSpinnersPage() {
        logger.info("scrolling to the Spinner list option");
        logger.info("tapping on Views to open Views screen");
        tap(spinnerLOLocator);
        return new SpinnerPage(driver);
    }

}
