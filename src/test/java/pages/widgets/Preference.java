package pages.widgets;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.Widget;
import org.openqa.selenium.WebElement;

/*
This widget encapsulates the Widget Title and Summary on the Headers Page
 */
public class Preference extends Widget {
    protected Preference(WebElement element) {
        super(element);
    }

    @AndroidFindBy(id = "android:id/title")
    private WebElement title;

    @AndroidFindBy(id = "android:id/summary")
    private WebElement description;

    public String getTitle() {
        return title.getText();
    }

    public String getDesc() {
        return description.getText();
    }
}
