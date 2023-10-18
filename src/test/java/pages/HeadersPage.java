package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.widgets.Preference;

import java.util.List;

public class HeadersPage extends BasePage {
    private static Logger logger = LoggerFactory.getLogger(HeadersPage.class);
    public HeadersPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(xpath = "//android.widget.ListView//android.widget.LinearLayout")
    private List<Preference> preferences;

    public int getPrefCount() {
        return preferences.size();
    }

    private Preference getPreference(int index) {
        logger.info("Getting the Preference Widget at index {}", index);
        return preferences.get(index);
    }

    public String getPrefTitle(int index) {
        Preference preference = getPreference(index);
        logger.info("Getting the Title of Preference Widget at index {}", index);
        return preference.getTitle();
    }

    public String getPrefDesc(int index) {
        Preference preference = getPreference(index);
        logger.info("Getting the Description of Preference Widget at index {}", index);
        return preference.getDesc();
    }

}
