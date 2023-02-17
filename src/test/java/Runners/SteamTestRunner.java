package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/SteamTests"}, glue = {"steam.steps"},
        plugin = {"json:target/cucumber.json"})
public class SteamTestRunner extends AbstractTestNGCucumberTests {
}
