package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpinnerPage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger(SpinnerPage.class);
    public SpinnerPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private WebElement colorDropdown = driver.findElement(AppiumBy.xpath("//android.widget.Spinner[@resource-id='io.appium.android.apis:id/spinner1']"));
    private By colorDropdownLocator = AppiumBy.xpath("//android.widget.Spinner[@resource-id='io.appium.android.apis:id/spinner1']");

    private By colorDropdownTextLocator = AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/text1']");

    private By getColorOptionLocator(String color) {
        return AppiumBy.xpath("//android.widget.CheckedTextView[@text='" + color + "']");
    }

    // Declare an enum for specific colors
    public enum Color {
        RED("red"),
        ORANGE("orange"),
        YELLOW("yellow"),
        GREEN("green"),
        BLUE("blue"),
        VIOLET("violet");

        private final String colorName;

        Color(String colorName) {
            this.colorName = colorName;
        }

        public String getColorName() {
            return colorName;
        }
    }

    public void selectColor(Color color) {
        logger.info("tapping on Color Dropdown");
        tap(colorDropdownLocator);
        logger.info("selecting {} color from the dropdown", color.getColorName());
        tap(getColorOptionLocator(color.getColorName()));
    }

    public String getColorValueFromDropdown() {
        return waitForVisibility(colorDropdownLocator)
                .findElement(colorDropdownTextLocator)
                .getText();
    }
}
