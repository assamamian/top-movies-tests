package basicTest;

import org.fluentlenium.adapter.testng.FluentTestNg;
import org.fluentlenium.configuration.FluentConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigParameters;
import webdriverfactory.ChromeDriver106Configuration;

import java.lang.reflect.Method;

@FluentConfiguration(configurationDefaults = ChromeDriver106Configuration.class)
public class FluentWebTest extends FluentTestNg{

    private static Logger logger = LoggerFactory.getLogger(FluentWebTest.class);

    protected ConfigParameters config = new ConfigParameters();

    protected String defaultUrl = "url";

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method, ITestContext context, Object[] datas) {
        logger.info("Test starting");
        try {super.beforeMethod(method, context);
           goTo(getDefaultUrl());
           getDriver().manage().window().maximize();
        } catch (RuntimeException e) {
            e.printStackTrace();
            logger.error("ERROR ILLEGAL STATE: " + e.getMessage());
        }
    }

    protected String getDefaultUrl() {
        return defaultUrl;
    }


    @AfterMethod(alwaysRun = true)
    @Override
    public void afterMethod(ITestResult result) {
        try {
            logger.info("Trying to close webdriver for test");
            super.afterMethod(result);
            logger.info("Webdriver is closed for test");
        }catch (Exception e) {}
        logger.info("Test ending");
    }

}
