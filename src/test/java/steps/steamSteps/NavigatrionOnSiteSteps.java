package steam.steps;

import framework.Logger;
import framework.driver.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import steam.model.SpecialOfferGamePane;
import steam.pageObjects.ActionGamesPage;
import steam.pageObjects.GamePage;
import steam.pageObjects.StoreHomePage;

import java.util.ArrayList;

public class NavigatrionOnSiteSteps {

    private static final Browser browser = Browser.getInstance();
    private static SpecialOfferGamePane game = null;

    @Given("Open start page on Steam")
    public void open_start_page_on_steam() {
        browser.navigate("https://store.steampowered.com/");
        browser.waitPageToLoad();
    }

    @When("I switch the site language to {string}")
    public void i_switch_the_site_language_to(String string) throws InterruptedException {
        StoreHomePage mainPage = new StoreHomePage();
        mainPage.mainMenuPC.switchLanguage(string);
        browser.waitPageToLoad();
    }
    @Then("the {string} language is removed from the list:")
    public void the_language_is_removed_from_the_list(String string) {
        StoreHomePage mainPage = new StoreHomePage();
        Assert.assertFalse(mainPage.mainMenuPC.isLanguageExists(string));
    }

    @When("I change the site language:")
    public void i_change_the_site_language(io.cucumber.datatable.DataTable dataTable) {
        StoreHomePage mainPage = new StoreHomePage();
        mainPage.mainMenuPC.switchLanguage(dataTable.asMap().get("Language"));
        browser.waitPageToLoad();
    }
    @Then("the active language is removed from the list:")
    public void the_active_language_is_removed_from_the_list(io.cucumber.datatable.DataTable dataTable) {
        StoreHomePage mainPage = new StoreHomePage();
        Assert.assertFalse(mainPage.mainMenuPC.isLanguageExists(dataTable.asMap().get("Language")));
    }
    @When("I select the {string} and {string}")
    public void i_select_the_and(String string, String string2) {
        StoreHomePage mainPage = new StoreHomePage();
        mainPage.navigationPC.navigateMenu(string, string2);
        browser.waitPageToLoad();
    }
    @Then("The {string} page is opened")
    public void the_page_is_opened(String string) {
        Assert.assertTrue(browser.getBrowserUri().contains(String.format("store.steampowered.com/category/%s/", string.toLowerCase())));
    }
    @When("I select the cheapest game from:")
    public void i_select_the_cheapest_game_from(io.cucumber.datatable.DataTable dataTable) {
        var args = new ArrayList<String>();
        args.add(dataTable.asMap().get("Carousel"));

        ActionGamesPage page = new ActionGamesPage(args);
        try
        {
            page.coockiesPage.acceptAllCoockies();
        }
        catch(TimeoutException e)
        {
            Logger.getInstance().warn("AcceptCookiesPage.not.found");
        }

        game = page.findCheapestActionGame();
        page.selectGameFromOffered(game);
        browser.waitPageToLoad();
    }
    @Then("The page of the game is opened")
    public void the_page_of_the_game_is_opened() {
        new GamePage(game.getGameName());
    }
}
