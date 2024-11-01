package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCatalog extends AbstractClass {
    WebDriver driver;
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//div[@class='card-body']")
    List<WebElement> ItemWebElements;
    By ByLocator = By.id("products");

    By ByLocator1 = By.xpath("button[contains(@class,'w-10 rounded')]");

    //actions
    public void ClickOnSelectedItems(List<String> needItems) throws InterruptedException
    {
        WaitelementToLoad(ByLocator);
        Thread.sleep(2000);
        List<WebElement> filterdWebelemets = ItemWebElements.stream().filter(p->{
            String txt = p.findElement(By.tagName("b")).getText();
            return needItems.stream().anyMatch(txt::contains);}).collect(Collectors.toList());
        System.out.println(filterdWebelemets.size());
        for(WebElement element : filterdWebelemets) {
            element.findElement(ByLocator1).click();
            Thread.sleep(2000);
        }

    }



}
