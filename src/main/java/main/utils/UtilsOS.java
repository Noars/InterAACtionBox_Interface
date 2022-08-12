package main.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UtilsOS {

    public static final String OS = System.getProperty("os.name").toLowerCase();

    public static boolean isWindows() {

        return (OS.contains("win"));

    }

    public static boolean isMac() {

        return (OS.contains("mac"));

    }

    public static boolean isUnix() {

        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));

    }

    public static boolean isConnectedToInternet() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static String getUserNameFromOSForPWA(){
        String username = System.getProperty("user.name").toLowerCase();
        return username.replace(" ","_");
    }

    public static String getUserNameFromOS(){
        return System.getProperty("user.name");
    }

}
