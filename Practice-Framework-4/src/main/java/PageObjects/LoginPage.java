package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractClass {

    WebDriver driver;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
        /*driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("bheeshmacharya@gmail.com");
        driver.findElement(By.xpath("//input[@formcontrolname='userPassword']")).
        sendKeys("Bheeshma@123");
        driver.findElement(By.cssSelector("input[name='login']")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.id("products")));*/

    @FindBy(id = "userEmail")
    WebElement userNameElement;
    By ByLocator1 = By.id("userEmail");
    @FindBy(xpath = "//input[@formcontrolname='userPassword']")
    WebElement passwordElement;
    @FindBy(css = "input[name='login']")
    WebElement LoginButtonElement;
    By ByLocator2 = By.id("products");

    @FindBy(css = ".ngx-toastr.toast-error")
    WebElement erromsg;
   // .ng-tns-c4-55.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
    //Action methods
    public void Url(String url)
    {
        driver.get(url);
    }
    public void loginWithCredentials(String UserName, String Password)
    {
        WaitelementToLoad(ByLocator1);
        userNameElement.sendKeys(UserName);
        passwordElement.sendKeys(Password);

    }
    public ProductCatalog clickOnloginButton()
    {
        LoginButtonElement.click();
        ProductCatalog pc = new ProductCatalog(driver);
        return pc;
    }
    public String ValidateError()
    {
        WaitelementToLoad1(erromsg);
        return erromsg.getText();
    }
}
