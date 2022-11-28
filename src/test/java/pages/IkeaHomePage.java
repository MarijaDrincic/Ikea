package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class IkeaHomePage extends BaseHelper
{
    WebDriver driver;
    public IkeaHomePage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(className = "search-wrapper")
    WebElement searchWrapper;
    @FindBy(name = "q")
    WebElement searchField;
    @FindBy (id = "search-box__searchbutton")
    WebElement searchButton;



    private void navigateToHomePage (String url)
    {
        driver.get(url);
    }

    private void acceptCookiesIfNeeded ()
    {
        List<WebElement> acceptCookiesButton = driver.findElements(By.id("onetrust-accept-btn-handler"));
        if (acceptCookiesButton.size() != 0) {
            acceptCookiesButton.get(0).click();
            wdWait.until(ExpectedConditions.invisibilityOf((acceptCookiesButton.get(0))));
        }

    }

    private void insertSearchTerm (String searchTerm)
    {
        wdWait.until(ExpectedConditions.visibilityOf(searchWrapper));
        searchField.sendKeys(searchTerm);
        searchButton.click();
    }


    public void searchFunctionalityTest (String url, String searchTerm)
    {
        navigateToHomePage(url);
        acceptCookiesIfNeeded();
        insertSearchTerm(searchTerm);
    }

}
