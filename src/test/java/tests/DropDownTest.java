package tests;

import org.testng.annotations.Test;
import pages.SpinnerPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DropDownTest extends BaseTest{
    @Test
    public void test_dropdown() {
        viewsPage = homePage.openViewsPage();
        spinnerPage = viewsPage.openSpinnersPage();
        spinnerPage.selectColor(SpinnerPage.Color.BLUE);
        assertThat(spinnerPage.getColorValueFromDropdown())
                .isEqualTo(SpinnerPage.Color.BLUE.getColorName());

    }
}
