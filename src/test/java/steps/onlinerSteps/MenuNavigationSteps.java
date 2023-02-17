package onliner.steps;

import framework.driver.Browser;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pageObjects.MainPage;
import org.testng.Assert;

public class MenuNavigationSteps {

    private static final Browser browser = Browser.getInstance();
    @When("I click on {string} item")
    public void i_click_on_item(String string) {
        var mainPage = new MainPage();
        mainPage.SelectMenu(string);
    }
    @Then("{string} is opened")
    public void is_opened(String string) {
        Assert.assertTrue(browser.getBrowserUri().contains(string));
    }



}
