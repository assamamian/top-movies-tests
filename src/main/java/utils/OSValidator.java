package utils;

public class OSValidator {

    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String ARCH = System.getProperty("os.arch");

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0 && ARCH.indexOf("x86_64") >=0);
    }

    public static boolean isMacM1() {
        return (OS.indexOf("mac") >= 0 && ARCH.indexOf("aarch64") >=0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }
}

