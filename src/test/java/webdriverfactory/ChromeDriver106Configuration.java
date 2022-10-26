package webdriverfactory;

import org.fluentlenium.configuration.ConfigurationDefaults;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigParameters;
import utils.OSValidator;

import java.util.HashMap;

public class ChromeDriver106Configuration extends ConfigurationDefaults {

    protected ConfigParameters config = new ConfigParameters();

    @Override
    public String getWebDriver() {
        if (OSValidator.isUnix()) {
            System.setProperty("webdriver.chrome.driver", config.getValue("chromedriver106linux"));
        } else if (OSValidator.isMac()) {
            System.setProperty("webdriver.chrome.driver", config.getValue("chromedriver106macos"));
        } else if (OSValidator.isMacM1()) {
            System.setProperty("webdriver.chrome.driver", config.getValue("chromedriver106macosm1"));
        } else {
            throw new RuntimeException("Not yet supported");
        }
        return "chrome";
    }

    @Override
    public Capabilities getCapabilities() {

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);

        DesiredCapabilities caps = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-disable-application-cache");
        options.addArguments("--disable-application-cache");
        options.addArguments("no-sandbox");
        options.addArguments("disable-extensions");
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");

        options.setExperimentalOption("prefs", chromePrefs);
        caps.setCapability(ChromeOptions.CAPABILITY, options);

        return caps;
    }
}
