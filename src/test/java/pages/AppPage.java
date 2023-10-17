package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppPage extends BasePage{
    private static Logger logger = LoggerFactory.getLogger(AppPage.class);
    public AppPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "Alert Dialogs")
    private WebElement alertDialogsLO;

    public AlertsPage openAlertsPage() {
        logger.info("tapping on Alert Dialogs to open Alert Dialogs screen");
        tap(alertDialogsLO);
        return new AlertsPage(driver);
    }
}
