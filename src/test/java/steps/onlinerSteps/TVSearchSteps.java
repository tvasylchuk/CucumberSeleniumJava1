package onliner.steps;

import framework.driver.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import onliner.pageObjects.CatalogPage;
import onliner.pageObjects.MainPage;
import onliner.pageObjects.TVPageObject;
import org.testng.Assert;
import org.testng.internal.collections.Pair;

import java.util.ArrayList;

public class TVSearchSteps {

    private static final Browser browser = Browser.getInstance();

    @Given("Open start page of Onliner")
    public void open_start_page_of_onliner() {
        browser.navigate("https://www.onliner.by/");
    }
    @When("I click on Catalog button")
    public void i_click_on_catalog_button() {
        var mainPage = new MainPage();
        mainPage.SelectMenu("Каталог");
    }
    @Then("Catalog web page is opened")
    public void catalog_web_page_is_opened() {
        Assert.assertTrue(browser.getBrowserUri().contains("catalog.onliner.by"));
    }
    @When("I select the following product categories in menu:")
    public void i_select_the_following_product_categories_in_menu(io.cucumber.datatable.DataTable dataTable) {
        CatalogPage catalogPage = new CatalogPage();
        catalogPage.selectProductFromCatalog(dataTable.asMap().get("Department")
                , dataTable.asMap().get("Category")
                , dataTable.asMap().get("Product"));
    }
    @Then("TV page is opened")
    public void tv_page_is_opened() {
        Assert.assertTrue(browser.getBrowserUri().contains("catalog.onliner.by/tv"));
    }
    @When("I select following filters:")
    public void i_select_following_filters(io.cucumber.datatable.DataTable dataTable) {

        var tvPage = new TVPageObject();
        tvPage.ConfirmLocation();

        ArrayList<String> tvMakers = new ArrayList<>();
        tvMakers.add( dataTable.asMap().get("Maker"));

        ArrayList<String> resolutions = new ArrayList<>();
        resolutions.add( dataTable.asMap().get("Resolution") );

        var diagonal = new Pair<>(dataTable.asMap().get("DiagonalFrom"), dataTable.asMap().get("DiagonalTo"));

        tvPage.SetProduceFilter(tvMakers);
        tvPage.SetTopPrice(dataTable.asMap().get("UpperPrice"));
        tvPage.SetDiagonal(diagonal);
        tvPage.SetResolutionFilter(resolutions);
        tvPage.WaitForSearch();
    }
    @Then("products with following attributes are displayed:")
    public void product_with_following_attributes_are_disabled(io.cucumber.datatable.DataTable dataTable) {
        var tvPage = new TVPageObject();
        var result = tvPage.getSearchResult();
        for (var item : result)
        {
            Assert.assertTrue(item.getProductName().startsWith(dataTable.asMap().get("Product")));
            Assert.assertTrue(item.getPrice()<=Integer.parseInt(dataTable.asMap().get("UpperPrice")));
            Assert.assertEquals(item.getResolution(), dataTable.asMap().get("Resolution"));
            Assert.assertEquals(item.getMaker(), dataTable.asMap().get("Maker"));
            Assert.assertTrue(item.getDiagonal()>=Integer.parseInt(dataTable.asMap().get("DiagonalFrom")));
            Assert.assertTrue(item.getDiagonal()<=Integer.parseInt(dataTable.asMap().get("DiagonalTo")));
        }
    }

}
