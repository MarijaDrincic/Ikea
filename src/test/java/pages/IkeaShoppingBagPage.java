package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class IkeaShoppingBagPage extends BaseHelper
{
    WebDriver driver;
    public IkeaShoppingBagPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(className = "cart-ingka-quantity-dropdown")
    WebElement quantityContainer;
    @FindBy (className = "product_prices__pjmpg")
    WebElement adjustedPrice;

    private void openQuantitySection ()
    {
        wdWait.until(ExpectedConditions.visibilityOf(quantityContainer));
        quantityContainer.click();

    }

    private void selectQuantity (String desiredQuantity)
    {
        Select quantity = new Select(driver.findElement(By.xpath("//*[@id=\"one-checkout\"]/main/div/div/div/div[18]/div[2]/div/div/div[2]/div[1]/div[1]/div/select")));
        quantity.selectByValue(desiredQuantity);
        wdWait.until(ExpectedConditions.visibilityOf(adjustedPrice));

    }

    public void changeQuantityOfProductInShoppingBag (String desiredQuantity)
    {
        openQuantitySection();
        selectQuantity(desiredQuantity);

    }


}
