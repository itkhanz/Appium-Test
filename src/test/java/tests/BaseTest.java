package tests;

import config.constants.PlatformOS;
import core.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.*;

import java.lang.reflect.Method;

public class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    AppiumDriver driver;
    HomePage homePage;
    ViewsPage viewsPage;
    SpinnerPage spinnerPage;
    AppPage appPage;
    AlertsPage alertsPage;

    @BeforeMethod(alwaysRun = true)
    public void setup(Method m) {
        logger.info("****** Started test:" + m.getName() + " ******");
        driver = DriverManager.initializeDriver(PlatformOS.ANDROID);
        homePage = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(Method m) {
        DriverManager.shutdownDriver();
        logger.info("****** Finished test:" + m.getName() + " ******");
    }
}
