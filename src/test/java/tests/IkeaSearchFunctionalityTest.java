package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.IkeaHomePage;
import pages.IkeaResultsPage;

public class IkeaSearchFunctionalityTest extends BaseTest
{
    @Test
    public void ikeaSearchFunctionalityTest () throws InterruptedException
    {
        String url = "https://www.ikea.com/rs/sr/";
        String searchTerm = "dekoracija";
        int numberOfProduct = 3;

        IkeaHomePage ikeaHomePage = new IkeaHomePage(driver);
        ikeaHomePage.searchFunctionalityTest(url,searchTerm);

        IkeaResultsPage ikeaResultsPage = new IkeaResultsPage(driver);
        String productTitle = ikeaResultsPage.selectDesiredProduct(numberOfProduct);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("pip-temp-price-module__name")));
        WebElement openedProduct = driver.findElement(By.className("pip-header-section__title--big"));
        String openedProductTitle = openedProduct.getText();

        Assert.assertTrue("Product titles don't match!",openedProduct.getText().toLowerCase().contains(productTitle.toLowerCase()));

        System.out.println("Title of product is: "+productTitle);
        System.out.println("Title of opened product is: "+openedProductTitle);


        Thread.sleep(3000);

    }

}
