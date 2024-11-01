package Tests;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E2E {
    WebDriver driver;
    WebDriverWait w;
    List<String> needItems;
    JavascriptExecutor js;

    @Test(priority = 1)
    public void logingfeature()
    {
        driver  = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        w = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("bheeshmacharya@gmail.com");
        driver.findElement(By.xpath("//input[@formcontrolname='userPassword']")).
                sendKeys("Bheeshma@123");
        driver.findElement(By.cssSelector("input[name='login']")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("products")));
    }
    //Testcase2 -> add the items(ZARA COAT 3", "ADIDAS ORIGINAL) to cart
    @Test(priority = 2)
    public void ProductCatalog() throws InterruptedException {
        ////div[@class='card-body']
        //b
        ////button[contains(@class,'w-10 rounded')]
        //.ng-tns-c4-37.toast-message.ng-star-inserted
        needItems=Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL");
        List<WebElement> webElements = driver.findElements(By
                .xpath("//div[@class='card-body']"));
        List<WebElement> filterdWebelemets =webElements.stream().filter(p->{
            String txt = p.findElement(By.tagName("b")).getText();
            return needItems.stream().anyMatch(txt::contains);}).collect(Collectors.toList());
        System.out.println(filterdWebelemets.size());
        for(WebElement element : filterdWebelemets)
        {
            element.findElement(By.xpath("button[contains(@class,'w-10 rounded')]"))
                    .click();
            Thread.sleep(2000);
        }
    }
    //check if the selected items are present inside the cart
    @Test(priority = 3)
    public void cartPage()
    {
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
        ////li/div//h3
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/div//h3")));
        List<WebElement> Cartwebelements = driver.findElements(By.xpath("//li/div//h3"));
        /*List<String> FilterdWebElements = Cartwebelements.stream().filter(p->{
            String st =p.getText();
            return needItems.stream().anyMatch(st::contains);
        }).map(p->p.getText()).collect(Collectors.toList());
        boolean areEqual = needItems.equals(FilterdWebElements);
        Assert.assertTrue(areEqual);*/
        List<String> FilterdWebElements = new ArrayList<>();
        for(WebElement elememt : Cartwebelements)
        {
            FilterdWebElements.add(elememt.getText());
        }
        boolean areEqual = needItems.equals(FilterdWebElements);
        Assert.assertTrue(areEqual);
        WebElement button =driver.findElement(By.cssSelector("div[class*='subtotal']  button[class*='btn']"));
        js =(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,900)");
        js.executeScript("arguments[0].click();", button);
    }
    @Test(priority = 4)
    public void OrderPage() throws InterruptedException {
        w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
        /*WebElement SelectCountryelement = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
        Actions a = new Actions(driver);
        //a.moveToElement(SelectCountryelement).click().click().sendKeys("ind").build().perform();
        //a.moveToElement(SelectCountryelement).click().sendKeys(Keys.SHIFT, "ind").build().perform();
        a.moveToElement(SelectCountryelement).click().keyDown(Keys.SHIFT).sendKeys("ind").build().perform();
       // a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();

        //Thread.sleep(2000);
        List<WebElement> countryElements = driver.findElements(By.xpath("//button//span"));
        for(WebElement element : countryElements)
        {
            if(element.getText().equalsIgnoreCase("India"))
            {
                element.click();
            }
        }
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        WebElement button1 =driver.findElement(By.cssSelector(".action__submit"));
        //JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",button1);*/
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')])[2]")).click();
        WebElement button1 =driver.findElement(By.cssSelector(".action__submit"));
        //JavascriptExecutor js1 = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",button1);

    }

}
