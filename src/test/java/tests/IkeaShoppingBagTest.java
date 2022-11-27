package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.IkeaHomePage;
import pages.IkeaProductPage;
import pages.IkeaResultsPage;
import pages.IkeaShoppingBagPage;

public class IkeaShoppingBagTest extends BaseTest
{
    @Test
    public void ikeaShoppingBagTest () throws InterruptedException
    {
        String url = "https://www.ikea.com/rs/sr/";
        String searchTerm = "dekoracija";
        int numberOfProduct = 3;
        String desiredQuantity = "5";

        IkeaHomePage ikeaHomePage = new IkeaHomePage(driver);
        ikeaHomePage.searchFunctionalityTest(url, searchTerm);

        IkeaResultsPage ikeaResultsPage = new IkeaResultsPage(driver);
        ikeaResultsPage.selectDesiredProduct(numberOfProduct);

        IkeaProductPage ikeaProductPage = new IkeaProductPage(driver);
        String priceOfProduct = ikeaProductPage.addToShoppingBagTest();

        IkeaShoppingBagPage ikeaShoppingBagPage = new IkeaShoppingBagPage(driver);
        ikeaShoppingBagPage.changeQuantityOfProductInShoppingBag(desiredQuantity);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("product_contents__ha7uc")));
        WebElement product = driver.findElement(By.className("product_contents__ha7uc"));
        WebElement totalPrice = product.findElement(By.className("price_total__OCe2h"));
        String priceInTotal = totalPrice.getText();

        float productPriceFloat = Float.parseFloat(priceOfProduct.replace(".", ""));
        float totalPriceFloat = Float.parseFloat(priceInTotal.replace(".","").replace(" ", "").replace("RSD", ""));

        Assert.assertTrue("Total price of products isn't correct",((Float.parseFloat(desiredQuantity))*productPriceFloat)==totalPriceFloat);

        System.out.println("Price of product is: "+priceOfProduct);
        System.out.println("Price in total is: "+priceInTotal);

        Thread.sleep(3000);

    }

}
