package config.owner;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:server.properties",
        "classpath:android.properties",
        "classpath:ios.properties"
})
public interface AppiumConfig extends Config {

    /****************** server.properties ******************/
    @Config.Key("server.ip")
    String ip();

    @Config.Key("server.port")
    int port();

    int command_timeout();

    /****************** android.properties ******************/
    @Config.Key("android.udid")
    String androidUDID();

    @Config.Key("android.appPackage")
    String appPackage();

    @Config.Key("android.appActivity")
    String appActivity();

    @Config.Key("android.appWaitActivity")
    String appWaitActivity();

    /****************** ios.properties ******************/
    @Config.Key("ios.udid")
    String iOSUDID();

    @Config.Key("ios.bundleid")
    String iOSBundleId();
}