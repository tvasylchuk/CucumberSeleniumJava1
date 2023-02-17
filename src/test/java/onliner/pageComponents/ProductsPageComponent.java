package onliner.pageComponents;

import framework.Logger;
import framework.driver.Browser;
import framework.elements.Label;
import org.openqa.selenium.By;

public class ProductsPageComponent {
    private static Browser browser;
    private static Logger logger = Logger.getInstance();
    private Label lbDepartment(String department) {
        return new Label(By.xpath("//span[@class='catalog-navigation-classifier__item-title-wrapper' and text()='"+department+"']/.."));
    }
    private Label lbCategory(String productCategoryName) {
        return new Label(By.xpath("//div[@class='catalog-navigation-list__category' and @data-id = '1']//div[@class='catalog-navigation-list__aside-title' and starts-with(text(),' "+productCategoryName+"')]"));
    }
    private Label lbProduct(String productName) {
        return new Label(By.xpath("//div[@class='catalog-navigation-list__aside-item catalog-navigation-list__aside-item_active']//div[@class='catalog-navigation-list__dropdown']//span[text()=' "+productName+" ']"));
    }

    public ProductsPageComponent()
    {
        browser = Browser.getInstance();
        logger.info("onliner.pageComponents.ProductsPageComponent.ctr.ready");
    }

    public void selectDepartment(String department) {
        lbDepartment(department).click();
        logger.info("onliner.pageComponents.ProductsPageComponent.selectDepartment.elementClick.done");
    }
    public void selectProductCategory(String category) {
        lbCategory(category).click();
        logger.info("onliner.pageComponents.ProductsPageComponent.selectProductCategory.elementClick.done");
    }
    public void selectProduct(String product) {
        lbProduct(product).click();
        logger.info("onliner.pageComponents.ProductsPageComponent.selectProduct.elementClick.done");
    }
}
