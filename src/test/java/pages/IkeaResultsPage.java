package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class IkeaResultsPage extends BaseHelper
{
    WebDriver driver;
    public IkeaResultsPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(className = "results")
    WebElement results;
    @FindBy (className = "filters__bar")
    WebElement filtersContainer;
    @FindBy (className = "filter-dropdown__wrapper")
    WebElement priceRangeSection;
    @FindBy (id = "search-results")
    WebElement searchResults;

    public String selectDesiredProduct (int numberOfProduct)
    {
        wdWait.until(ExpectedConditions.visibilityOf(searchResults));
        List<WebElement> products = results.findElements(By.className("product-fragment__info"));
        WebElement product = products.get(numberOfProduct).findElement(By.className("header-section__title"));
        String productTitle = product.getText();
        product.click();
        return productTitle;

    }

    private void selectPriceFilter ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(filtersContainer));
        List<WebElement> filters = filtersContainer.findElements(By.className("filter-button__container"));
        for (WebElement filter: filters)
        {
            if (filter.getText().contains("Cena"))
                filter.click();
        }

    }

    private void selectPriceRange (String priceRange)
    {
        wdWait.until(ExpectedConditions.visibilityOf(priceRangeSection));
        List<WebElement> prices = priceRangeSection.findElements(By.className("checkbox"));
        for (WebElement price: prices )
        {
            if (price.getText().contains(priceRange))
                price.click();
        }

    }


    public void selectPriceRangeTest (String priceRange)
    {
        selectPriceFilter();
        selectPriceRange(priceRange);

    }

}
