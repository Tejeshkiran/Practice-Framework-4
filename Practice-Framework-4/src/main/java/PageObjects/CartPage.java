package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends AbstractClass {

    WebDriver driver;
    JavascriptExecutor js;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//li/div//h3")
    List<WebElement> Cartwebelements;

    @FindBy(css = "div[class*='subtotal']  button[class*='btn']")
    WebElement button;


    public boolean verifyselecteditems(List<String> needItems)
    {

        List<String> FilterdWebElements = new ArrayList<>();
        for(WebElement elememt : Cartwebelements)
        {
            FilterdWebElements.add(elememt.getText());
        }
        boolean areEqual = needItems.equals(FilterdWebElements);
        return areEqual;
    }
    public OrderPage clickOnCheckout()
    {
        js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,900)");
        js.executeScript("arguments[0].click();", button);
        return new OrderPage(driver);
    }

}
