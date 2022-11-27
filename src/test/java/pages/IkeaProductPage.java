package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class IkeaProductPage extends BaseHelper
{
    WebDriver driver;
    public IkeaProductPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(className = "pip-buy-module__buttons")
    WebElement addToShoppingBagButton;
    @FindBy (className = "rec-link")
    WebElement goToShoppingBagLink;
    @FindBy (className = "rec-sheets__content-wrapper")
    WebElement proceedToShoppingCartPopUpWindow;



    private String addProductToShoppingBag ()
    {
        WebElement priceText = driver.findElement(By.className("pip-temp-price__integer"));
        String priceOfProduct = priceText.getText();
        js.executeScript("arguments[0].scrollIntoView();",addToShoppingBagButton);
        wdWait.until(ExpectedConditions.elementToBeClickable(addToShoppingBagButton));
        addToShoppingBagButton.click();
        return priceOfProduct;

    }

    private void continueToShoppingBag ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(proceedToShoppingCartPopUpWindow));
        goToShoppingBagLink.click();

    }

    public String addToShoppingBagTest ()
    {
        String priceOfProduct = addProductToShoppingBag();
        continueToShoppingBag();
        return priceOfProduct;

    }


}
