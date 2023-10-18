package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreferencePage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger(PreferencePage.class);
    public PreferencePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "8. Headers")
    private WebElement headersLO;

    public HeadersPage openHeadersPage() {
        logger.info("tapping on Headers to open Headers screen");
        tap(headersLO);
        return new HeadersPage(driver);
    }
}
