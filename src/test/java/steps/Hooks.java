package steps;

import framework.Logger;
import framework.driver.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    private static Browser browser;
    public static Scenario scenario;

    @Before
    public void setUp(Scenario scenario)
    {
        Hooks.scenario = scenario;
        browser = Browser.getInstance();
        browser.maximise();
    }

    @After
    public void tearDown()
    {
        browser.exit();
    }
}
