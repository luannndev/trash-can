package dev.luan.trashcan.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class OSUtils {

    public String getAppDataFolder() {
        String os = System.getProperty("os.name").toUpperCase();
        if (os.contains("WIN")) {
            return System.getenv("APPDATA");
        } else if (os.contains("MAC")) {
            return System.getProperty("user.home") + "/Library/Application Support";
        } else if (os.contains("NUX")) {
            return System.getProperty("user.home");
        }
        return System.getProperty("user.dir");
    }

}