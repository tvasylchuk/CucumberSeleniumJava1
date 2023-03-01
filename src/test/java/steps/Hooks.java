package steps;

import framework.driver.Browser;
import framework.driver.BrowserType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    private static Browser browser;
    public static Scenario scenario;
    public static Logger LOGGER = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        Hooks.scenario = scenario;
        String scenarioName = scenario.getName();
        BrowserType browserParameter = getParameter(System.getProperty("browser"));
        Browser.initCurrentBrowser(browserParameter);
        browser = Browser.getInstance();
        browser.maximise();
    }

    @After
    public void tearDown() {
        if (!scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        browser.exit();
    }

    private BrowserType getParameter(String name) {
        if (name == null || name.isEmpty())
            return BrowserType.Chrome;

        return BrowserType.valueOf(System.getProperty(name));
    }

    public static byte[] getScreenshot()
    {
        return ((TakesScreenshot) browser.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
