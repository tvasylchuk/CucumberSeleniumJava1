package steps.onlinerSteps;

import framework.driver.Browser;
import io.cucumber.java.Scenario;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Hooks {
    protected static Browser browser;
    protected static final Logger logger = LogManager.getLogger(Hooks.class);
    protected static Scenario scenario;

    public void setUp()
    {
        browser = Browser.getInstance();
        browser.maximise();
    }

    public void tearDown()
    {
        browser.exit();
    }
}
