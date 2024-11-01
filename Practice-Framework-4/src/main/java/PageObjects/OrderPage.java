package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends AbstractClass {
    WebDriver driver;
    JavascriptExecutor js1;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    By Bylocator= By.cssSelector("input[placeholder='Select Country']");
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement CountrySearchelement;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement SelectCountry;
    @FindBy(css = ".action__submit")
    WebElement SubmitButton;

    public void selectCountry() throws InterruptedException {
        WaitelementToLoad(Bylocator);
        Thread.sleep(2000);
        CountrySearchelement.click();
        Actions a = new Actions(driver);
        a.sendKeys(CountrySearchelement,"Ind").build().perform();
        SelectCountry.click();
    }
    public void clickonPlaceOrder()
    {
        js1 = (JavascriptExecutor)driver;
        js1.executeScript("arguments[0].click();",SubmitButton);
    }
}
