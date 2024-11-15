package com.redhat.widget.ui;

import java.io.IOException;
import java.time.Duration;

import com.redhat.widget.helper.DatabaseHelper;
import com.redhat.widget.helper.WebDriverConfigProperties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AbstractSeleniumTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractSeleniumTest.class);

    protected static WebDriver WEB_DRIVER;

    protected static JavascriptExecutor JS_EXECUTOR;

    private static DatabaseHelper databaseHelper;

    @BeforeAll
    public static void setUp(@Autowired WebDriverConfigProperties properties,
                             @Autowired DatabaseHelper databaseHelper) throws IOException, InterruptedException {

        AbstractSeleniumTest.databaseHelper = databaseHelper;

        databaseHelper.deleteAll();

        FirefoxOptions options = new FirefoxOptions();
        options.setImplicitWaitTimeout(Duration.ofSeconds(properties.getTimeout()));

        // default is not headless
        LOG.debug("headless {}", properties.isHeadless());
        if (properties.isHeadless()) {
            options.addArguments("--headless");
        }

        WEB_DRIVER = new FirefoxDriver(options);
        JS_EXECUTOR = (JavascriptExecutor) WEB_DRIVER;
    }

    @AfterAll
    public static void tearDown() throws IOException, InterruptedException {

        if (WEB_DRIVER != null) {
            WEB_DRIVER.quit();
        }

        databaseHelper.deleteAll();
    }

}
