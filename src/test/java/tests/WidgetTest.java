package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WidgetTest extends BaseTest {

    @BeforeMethod
    private void navigate_to_headersPage() {
        preferencePage = homePage.openPreferencePage();
        headersPage = preferencePage.openHeadersPage();
    }

    /*
    This test case validates the total count of preferences on Headers Page
    Each Preference is stored as Widget
     */
    @Test
    public void TC_04_header_pref_count_test() {
        assertThat(headersPage.getPrefCount()).isEqualTo(3);
    }

    /*
    This test case validates the title and summary of second preference on Headers Page
    Each Preference is stored as Widget
     */
    @Test
    public void TC_05_header_pref_title_desc_test() {
        assertThat(headersPage.getPrefTitle(1)).isEqualTo("Prefs 2");
        assertThat(headersPage.getPrefDesc(1)).isEqualTo("Some other preferences you can see.");
    }
}
