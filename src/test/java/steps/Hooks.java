package steps;

import framework.Logger;
import framework.driver.Browser;
import framework.driver.BrowserType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private static Browser browser;

    private static BrowserType currentBrowser;
    public static Scenario scenario;

    @Before
    public void setUp(Scenario scenario)
    {
        Hooks.scenario = scenario;
        currentBrowser = Browser.initCurrentBrowser(getParameter(System.getProperty("browser")));
        browser = Browser.getInstance();
        browser.maximise();
    }

    @After
    public void tearDown()
    {
        browser.exit();
    }

    private BrowserType getParameter(String name) {
        String value = System.getProperty(name);
        if (value == null || value.isEmpty())
            return BrowserType.Chrome;

        return BrowserType.valueOf(value);
    }
}
