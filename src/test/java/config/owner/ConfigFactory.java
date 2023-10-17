package config.owner;

import org.aeonbits.owner.ConfigCache;

public class ConfigFactory {
    public static AppiumConfig getAppiumProps() {
        return ConfigCache.getOrCreate(AppiumConfig.class);
    }
}
