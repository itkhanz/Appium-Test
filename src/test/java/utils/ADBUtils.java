package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Utility class for ADB Shell commands
Android Debug Bridge
 */
public class ADBUtils {
    private static Logger logger = LoggerFactory.getLogger(ADBUtils.class);

    /*
    adb version
    This method checks if ADB is installed on the system
    This is essential as an initial check to execute adb commands later
     */
    public static boolean isADBInstalled() {
        try {
            // Execute 'adb version' command to check if ADB is installed and in the PATH
            Process process = Runtime.getRuntime().exec("adb version");

            // Wait for the process to complete
            int exitCode = process.waitFor();

            // If ADB is installed, 'adb version' will return 0 (success)
            return (exitCode == 0);
        } catch (IOException | InterruptedException e) {
            // ADB is not installed or not in the PATH
            return false;
        }
    }

    /*
    adb devices
    This will list all of the devices that are connected to PC via USB Cable
     */
    public static List<String> getAndroidDevicesUDIDs() {
        List<String> connectedDevices = new ArrayList<>();
        try {
            // Command to list connected Android devices
            String command = "adb devices";

            // Create a ProcessBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Skip the first line and empty lines
                if (!line.contains("List of devices attached") && !line.isEmpty()) {
                    // Extract the device ID (UDID)
                    String[] parts = line.split("\\s+");
                    if (parts.length > 0) {
                        connectedDevices.add(parts[0]);
                    }
                }
            }

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return connectedDevices;
    }


    /**
     * This method will filter the device UDIDs that are not connected to internet via Wi-Fi
     * @param devicesUDIDs list of UDIDs of all the android devices
     * @return list of UDIDs of the devices not connected to internet via Wi-Fi
     */
    public static List<String> getDevicesNotConnectedToInternet(List<String> devicesUDIDs) {
        List<String> devicesNotConnectedToInternet = new ArrayList<>();

        logger.info("*************************************************");
        logger.info("Found Android devices (UDIDs) with Wi-Fi SSID:");
        for (String udid : devicesUDIDs) {
            String wifiSSID = getConnectedWiFiSSID(udid);
            if (wifiSSID == null) {
                devicesNotConnectedToInternet.add(udid);
                wifiSSID = "Null";
            }
            logger.info("UDID: " + udid + ", Wi-Fi SSID: " + wifiSSID);
        }
        logger.info("*************************************************");
        return devicesNotConnectedToInternet;
    }

    /**
     * This method will return the name of the Wi-Fi SSID for particular device
     * If device is not connected to internet, it returns null
     * @param udid UDID of the android device
     * @return Wi-Fi SSID of the android device
     */
    public static String getConnectedWiFiSSID(String udid) {
        try {
            // Command to check Wi-Fi SSID for the device with a specific UDID
            String command = "adb -s " + udid + " shell dumpsys wifi | grep 'mWifiInfo SSID:'";

            // Create a ProcessBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                /*if (line.contains("mWifiInfo SSID:")) {
                    return line.split(": ")[1].trim();
                }*/
                if (!line.contains("<unknown ssid>")) {
                    // Extract the SSID from the line
                    return line.split(": ")[1].trim();
                }
            }

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * adb -s YOUR_UDID shell svc wifi enable
     * adb -s YOUR_UDID shell svc wifi disable
     * This will enable or disable the Wi-Fi on particular android device
     * @param udid UDID of the android device
     * @param enableWiFi boolean flag true to Turn On, false to Turn Off the Wi-Fi
     */
    public static void controlWiFi(String udid, boolean enableWiFi) {
        try {
            String command;
            if (enableWiFi) {
                command = "adb -s " + udid + " shell svc wifi enable";
            } else {
                command = "adb -s " + udid + " shell svc wifi disable";
            }

            // Create a ProcessBuilder to execute the command
            Process process = new ProcessBuilder("bash", "-c", command).start();

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method checks if the WiFi on particular device has been switched On or off
     * @param udid UDID of the device
     * @return true if WiFi is enabled, else false if it is disabled
     */
    public static boolean isWiFiEnabled(String udid) {
        try {
            // Command to check Wi-Fi status for the device with a specific UDID
            String command = "adb -s " + udid + " shell dumpsys wifi | grep 'Wi-Fi is'";

            // Create a ProcessBuilder to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c", command);
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Wi-Fi is enabled")) {
                    return true;
                }
            }

            // Wait for the process to complete
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
