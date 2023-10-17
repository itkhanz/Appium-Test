package tests;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UnexpectedAlertsTest extends BaseTest{

    /*
    This method validates the auto-handling of unexpected popups in specific parts of app
    Assuming that the popup with Ok Cancel Dialog can appear anytime automatically, we try to handle it
    PopupHandling mechanism is called before each action (element interaction) in AlertsPage
    This ensures that we do not have to explicitly close the popup if it is appeared
    In this example, we are not closing the unexpected popup explicitly after calling openOkCancelDialog()
    So before the next action openListDialog() takes place, it checks and handles unexpected popup automatically
     */
    @Test
    public void TC_02_handle_unexpected_popups_automatically_test() {
        appPage = homePage.openAppPage();
        alertsPage = appPage.openAlertsPage();
        alertsPage.openOkCancelDialog();
        alertsPage.openListDialog();
        assertThat(alertsPage.isHeaderTitleInListDialogDisplayed()).isTrue();
    }
}
