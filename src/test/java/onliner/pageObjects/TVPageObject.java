package onliner.pageObjects;

import framework.elements.*;
import framework.pageObjects.BasePage;
import onliner.pageComponents.ProductsPageComponent;
import onliner.pageComponents.SearchResultPageComponent;
import onliner.model.TVCriterias;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.internal.collections.Pair;

import java.util.List;
import java.util.NoSuchElementException;

public class TVPageObject extends BasePage {
    private final ProductsPageComponent productsPageComponent = new ProductsPageComponent();
    private SearchResultPageComponent searchResult = null;
    private final Label Title = new Label (By.tagName("h1"));
    private final Button btnLocation = new Button(By.xpath("//div[@class='popover-style__content']//span[contains(text(), 'Да, верно')]"), "Confirm location button");
    private final Label lbFilterTag = new Label(By.className("schema-tags__text"));
    private final Button btnSearchProducts = new Button(By.xpath("//div[@class='schema-filter-button__state schema-filter-button__state_initial schema-filter-button__state_disabled schema-filter-button__state_control schema-filter-button__state_animated']"), "Search product");
    private final TextBox UpperPrice = new TextBox(By.xpath("//input[@class='schema-filter-control__item schema-filter__number-input schema-filter__number-input_price' and @placeholder='до']"));
    private final ComboBox diagonalSizeFrom = new ComboBox(By.xpath("//select[@class='schema-filter-control__item' and contains(@data-bind, 'facet.value.from')]"));
    private final ComboBox diagonalSizeTo = new ComboBox(By.xpath("//select[@class='schema-filter-control__item' and contains(@data-bind, 'facet.value.to')]"));
    private final TextBoxGroup txtGroupProducts = new TextBoxGroup(By.className("schema-product"));

    private FilterCheckBox chFilterByMaker(String filterCategory, String filterName)
    {
        String lct = "//div[@class='schema-filter__label']/span[text()='"+filterCategory+"']/../..//ul[@class='schema-filter__list']//input[@type='checkbox' and @value='"+filterName+"']";
        return new FilterCheckBox(By.xpath(lct));
    }

    public TVPageObject()
    {
        super();
    }

    @Override
    public String GetTitle()
    {
        return Title.getTextFromElement();
    }

    public void SetProduceFilter(List<String> tvMakers)
    {
        for (String maker: tvMakers)
        {
            var producerFilter = chFilterByMaker("Производитель", maker.toLowerCase());
            producerFilter.setProducerFilter();
        }
    }

    public void SetResolutionFilter(List<String> resolutions)
    {
        for(String resolution:resolutions)
        {
            var resolutionFilter = chFilterByMaker("Разрешение", resolution);
            resolutionFilter.setProducerFilter();
        }
    }

    public void SetTopPrice(String price)
    {
        UpperPrice.sendKey(price.toString());
    }

    public void SetDiagonal(Pair<String, String> diagonals)
    {
        diagonalSizeFrom.selectItemByText(diagonals.first());
        diagonalSizeTo.selectItemByText(diagonals.second());
    }

    public void ConfirmLocation()
    {
        try
        {
            browser.getWait().until(ExpectedConditions.visibilityOfElementLocated(btnLocation.getLocator()));
            btnLocation.getElement();
            btnLocation.scrollPageTillElementVisible();
            btnLocation.click();
        }
        catch (NoSuchElementException exception)
        {
            logger.info("onliner.pageObjects.TVPageObject.location.dialog.none");
        }
    }

    public String GetFilterTagText()
    {
        return lbFilterTag.getTextFromElement();
    }


    public void WaitForSearch()
    {
        btnSearchProducts.waitTillButtonAnimated();
    }

    public List<TVCriterias> getSearchResult()
    {
        searchResult = new SearchResultPageComponent();
        return searchResult.GetProductSearchResult();
    }
}
