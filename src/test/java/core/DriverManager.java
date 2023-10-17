package core;

import config.constants.PlatformOS;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static config.owner.ConfigFactory.getAppiumProps;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    private static void setDriver(AppiumDriver dr) {
        driver.set(dr);
    }

    public static AppiumDriver initializeDriver(PlatformOS platform){
        if(DriverManager.getDriver()==null) {
            try {
                new DriverManager(platform);
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize the DriverManager");
            }
        }
        //DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return DriverManager.getDriver();
    }

    /**
     * Closes the driver session
     */
    public static void shutdownDriver(){
        if(DriverManager.getDriver() != null) {
            try {
                DriverManager.getDriver().quit();
                DriverManager.driver.remove();
            } catch (Exception e) {
                throw new RuntimeException("Failed to quit the Driver from thread: " + Thread.currentThread().getId());
            }
        }
    }

    private DriverManager(PlatformOS platform) throws MalformedURLException {
        AppiumDriver driver;

        //Appium Server URL and port
        URL url = new URL(getAppiumProps().ip() + ":" + getAppiumProps().port());

        switch (platform) {
            case ANDROID -> {
                String appUrlAndroid = System.getProperty("user.dir") + "//src//test//resources//app//ApiDemos-debug.apk";
                UiAutomator2Options androidOptions = new UiAutomator2Options()
                        .setUdid(getAppiumProps().androidUDID())
                        //.setAppPackage(getAppiumProps().appPackage())
                        //.setAppActivity(getAppiumProps().appActivity())
                        .setApp(appUrlAndroid)
                        .setAutoGrantPermissions(true)
                        .setAppWaitActivity(getAppiumProps().appWaitActivity())
                        .setNewCommandTimeout(Duration.ofSeconds(getAppiumProps().command_timeout()))
                        ;
                driver = new AndroidDriver(url, androidOptions);
            }
            case IOS -> {
                String appUrliOS = System.getProperty("user.dir") + "//src//test//resources//app//QATest.zip";
                XCUITestOptions iOSOptions = new XCUITestOptions()
                        .setUdid(getAppiumProps().iOSUDID())
                        //.setBundleId(getAppiumProps().iOSBundleId())
                        .setApp(appUrliOS)
                        .setAutoAcceptAlerts(true)
                        .setNewCommandTimeout(Duration.ofSeconds(getAppiumProps().command_timeout()))
                        ;
                driver = new IOSDriver(url, iOSOptions);
            }
            default -> throw new RuntimeException("Unable to create session with platform: " + platform);
        }
        DriverManager.setDriver(driver);
    }
}
