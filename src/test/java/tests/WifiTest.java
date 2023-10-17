package tests;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.ADBUtils;

import java.util.List;

public class WifiTest {
    private static Logger logger = LoggerFactory.getLogger(WifiTest.class);

    /*
     * This test will perform the following actions using ADB Shell commands
     * 1. Get all the android devices UDIDs that are connected to system
     * 2. Filter the devices that are not connected to internet over Wi-Fi
     * For each of above filtered devices, it will iterate over and
     * 3. Disable (Switch Off) the Wi-Fi of device
     * 4. Verify that the Wi-Fi has been disabled/enabled for particular device
     */
    @Test
    public void TC03_disable_wifi_of_all_devices_not_connected_to_internet_test() {
        if (ADBUtils.isADBInstalled()) {
            List<String> devicesUDIDs = ADBUtils.getAndroidDevicesUDIDs();
            List<String> devicesNotConnectedToInternet = ADBUtils.getDevicesNotConnectedToInternet(devicesUDIDs);

            if (!devicesNotConnectedToInternet.isEmpty()) {
                logger.info("Disabling the Wi-Fi of devices that are not connected to Internet");

                SoftAssertions softly = new SoftAssertions();

                for (String udid : devicesNotConnectedToInternet) {
                    boolean wifiState = false;
                    ADBUtils.controlWiFi(udid, wifiState); // Set to true to enable Wi-Fi, false to disable Wi-Fi
                    logger.info("Disabled Wi-Fi for UDID: {}", udid);

                    boolean isWiFiEnabled = ADBUtils.isWiFiEnabled(udid);
                    if (isWiFiEnabled) {
                        logger.info("Wi-Fi is enabled on the device with UDID: {}", udid);
                    } else {
                        logger.info("Wi-Fi is disabled on the device with UDID: {}", udid);
                    }
                    softly.assertThat(isWiFiEnabled).isEqualTo(wifiState);
                }
                softly.assertAll();
            } else {
                logger.info("All found devices are connected to Internet via Wi-Fi");
            }
        } else {
            Assertions.fail("ADB is not installed or not in the system's PATH.");
        }

    }


}
