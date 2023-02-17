package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/"}, glue = {"steps"},
        plugin = {"json:target/cucumber.json"})
public class TestRunner extends AbstractTestNGCucumberTests {
}


