package tests;

import org.testng.annotations.Test;
import pages.SpinnerPage;

import static org.assertj.core.api.Assertions.assertThat;

public class DropDownTest extends BaseTest{

    /**
     * This test performs following series of steps:
     * Click on Views Tab
     * Scroll down to the Spinner tab (which is not initially visible on-screen) and click
     * Select blue color from the dropdown list
     * Verify that the blue color is selected in dropdown
     */
    @Test
    public void TC01_dropdown_test() {
        viewsPage = homePage.openViewsPage();
        spinnerPage = viewsPage.openSpinnersPage();
        spinnerPage.selectColor(SpinnerPage.Color.BLUE);
        assertThat(spinnerPage.getColorValueFromDropdown())
                .isEqualTo(SpinnerPage.Color.BLUE.getColorName());

    }
}
