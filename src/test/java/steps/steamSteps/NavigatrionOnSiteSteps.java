package steps.steamSteps;

import framework.Logger;
import framework.driver.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import steam.model.SpecialOfferGamePane;
import steam.pageObjects.ActionGamesPage;
import steam.pageObjects.AgeVerificationPage;
import steam.pageObjects.GamePage;
import steam.pageObjects.StoreHomePage;

import java.util.ArrayList;

public class NavigatrionOnSiteSteps {

    private static final Browser browser = Browser.getInstance();
    private static final Logger logger = framework.Logger.getInstance();
    private static SpecialOfferGamePane game = null;

    @Given("Open start page on Steam")
    public void open_start_page_on_steam() {
        browser.navigate("https://store.steampowered.com/");
        browser.waitPageToLoad();
        logger.info("browser.wait.to.load.page : 'https://store.steampowered.com/'");
    }

    @When("I switch the site language to {string}")
    public void i_switch_the_site_language_to(String string) {
        StoreHomePage mainPage = new StoreHomePage();
        logger.info("page 'https://store.steampowered.com/' is ready");
        mainPage.mainMenuPC.switchLanguage(string);
        logger.info(String.format("StoreHomePage.mainPage.switchLanguage: %s", string));
        browser.waitPageToLoad();
        logger.info("browser.wait.to.load.page : 'https://store.steampowered.com/'");
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
        logger.info("page 'https://store.steampowered.com/' is ready");
        mainPage.navigationPC.navigateMenu(string, string2);
        logger.info(String.format("StoreHomePage.mainPage.navigationPage.Menu: %s1 -> %s2", string, string2));
        browser.waitPageToLoad();
        logger.info(String.format("browser.wait.to.load.page : 'store.steampowered.com/category/%s/'", string2.toLowerCase()));
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
        logger.info(String.format("page '%s' is ready", browser.getBrowserUri()));
        try
        {
            page.coockiesPage.acceptAllCoockies();
            logger.info("AcceptCookiesPage.all.coockies.accepted");
        }
        catch(TimeoutException e)
        {
            logger.warn("AcceptCookiesPage.not.found");
        }

        game = page.findCheapestActionGame();
        logger.info(String.format("cheapest.game.found : '%s'", game.getGameName()));
        page.selectGameFromOffered(game);
        browser.waitPageToLoad();
    }

    @When("the customer age is verified")
    public void the_customer_age_is_verified() {
        if (browser.getBrowserUri().contains("agecheck"))
        {
            var ageCheckPage = new AgeVerificationPage();
            ageCheckPage.confirmAge("1", "January", "1984");
        }
        else {
            Logger.getInstance().info("steam.pageObjects.AgeVerificationPage.not.shown");
        }
        browser.waitPageToLoad();
    }

    @Then("The page of the game is opened")
    public void the_page_of_the_game_is_opened() {

        new GamePage(game.getGameName());
        logger.info(String.format("cheapest.game.page.selected : '%s'", browser.getBrowserUri()));
    }
}
