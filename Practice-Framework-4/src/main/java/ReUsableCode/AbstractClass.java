package ReUsableCode;

import PageObjects.CartPage;
import PageObjects.OrderHystoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AbstractClass {
    WebDriver driver;
    public AbstractClass(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    WebDriverWait w;
    public void WaitelementToLoad(By ByLocator)
    {
        w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOfElementLocated(ByLocator));
    }
    public void WaitelementToLoad1(WebElement element)
    {
        w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOf(element));
    }

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartElement;
    @FindBy(css = "button[routerlink ='/dashboard/myorders']")
    WebElement ordersButtonElemet;
    By LocaterElemet = By.cssSelector("button[routerlink ='/dashboard/myorders']");
    By ByLocator = By.xpath("//li/div//h3");

    public CartPage clickOnCart()
    {
        cartElement.click();
        WaitelementToLoad(ByLocator);
        CartPage cP = new CartPage(driver);
        return cP;

    }
    public OrderHystoryPage gotoOrderHystoryPage()
    {

        WaitelementToLoad(LocaterElemet);
        ordersButtonElemet.click();
        OrderHystoryPage Ohp =new OrderHystoryPage(driver);
        return Ohp;
    }


}
