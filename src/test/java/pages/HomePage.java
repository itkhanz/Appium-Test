package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger(HomePage.class);
    public HomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "App")
    private WebElement appLO;

    @AndroidFindBy(accessibility = "Views")
    private WebElement viewsLO;

    public ViewsPage openViewsPage() {
        logger.info("tapping on Views to open Views screen");
        tap(viewsLO);
        return new ViewsPage(driver);
    }

    public AppPage openAppPage() {
        logger.info("tapping on App to open App screen");
        tap(appLO);
        return new AppPage(driver);
    }
}
