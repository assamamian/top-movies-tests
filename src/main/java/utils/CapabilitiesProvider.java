package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class CapabilitiesProvider {

    private ConfigParameters config;
    private String build;

    public CapabilitiesProvider(ConfigParameters configParameters) {
        config = configParameters;
    }

    public DesiredCapabilities getCapabilities() {
        String browser = config.getValue("browser");
        DesiredCapabilities caps = new DesiredCapabilities();

        build = config.getValue("build") + "_" + config.getValue("buildTimestamp");
        caps.setCapability("build", build);

        if (browser.equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            // INIT CHROME OPTIONS
            Map<String, Object> prefs = new HashMap<String, Object>();
            Map<String, Object> profile = new HashMap<String, Object>();
            Map<String, Object> contentSettings = new HashMap<String, Object>();
            // SET CHROME OPTIONS
            contentSettings.put("geolocation", 1);// 0 - Default, 1 - Allow, 2 - Block
            profile.put("managed_default_content_settings", contentSettings);
            prefs.put("profile", profile);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("-disable-application-cache");
            options.addArguments("--disable-application-cache");
            options.addArguments("no-sandbox");
            options.addArguments("disable-extensions");
            options.addArguments("--start-maximized");
            options.addArguments("--incognito");
            options.addArguments("--ignore-certificate-errors");
            caps.setCapability(ChromeOptions.CAPABILITY, options);
        }
        return caps;
    }

    public String getBuildName() {
        return build;
    }

}
