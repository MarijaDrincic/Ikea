package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.IkeaHomePage;
import pages.IkeaResultsPage;

import java.util.List;

public class IkeaPriceRangeTest extends BaseTest
{
    @Test
    public void ikeaPriceRangeTest () throws InterruptedException
    {
        String url = "https://www.ikea.com/rs/sr/";
        String searchTerm = "dekoracija";
        String priceRange = "2.000 - 3.999 RSD";
        String minPrice = "2000";
        String maxPrice = "3990";

        IkeaHomePage ikeaHomePage = new IkeaHomePage(driver);
        ikeaHomePage.searchFunctionalityTest(url, searchTerm);

        IkeaResultsPage ikeaResultsPage = new IkeaResultsPage(driver);
        ikeaResultsPage.selectPriceRangeTest(priceRange);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("results__list")));
        WebElement resultsContainer = driver.findElement(By.className("results__list"));

        List<WebElement> productsList = resultsContainer.findElements(By.className("product-fragment__info"));

        for (WebElement product: productsList)
        {
            WebElement productTitle = product.findElement(By.className("header-section__title"));
            WebElement priceOfProduct = product.findElement(By.className("pip-price__integer"));

            String priceOfProductText = priceOfProduct.getText().replace(".", "");
            float price = Float.parseFloat(priceOfProductText);
            Assert.assertTrue("Price is not in selected range for product:" +productTitle,
                    (price>= Float.parseFloat(minPrice) && price <= Float.parseFloat(maxPrice)));

            String productTitleText = productTitle.getText();
            System.out.println("Title of product is: "+productTitleText);
            System.out.println("Price of product is: "+priceOfProductText);

        }


        Thread.sleep(3000);
    }


}
