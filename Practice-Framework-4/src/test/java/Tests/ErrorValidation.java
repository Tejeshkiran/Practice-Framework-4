package Tests;

import PageObjects.CartPage;
import PageObjects.OrderPage;
import PageObjects.ProductCatalog;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ErrorValidation extends BaseTest {
    WebDriver driver;
    WebDriverWait w;
    List<String> needItems;
    JavascriptExecutor js;
    ProductCatalog pc;
    CartPage cP;
    OrderPage Op;

    @Test(groups = {"negativeTestcase"},retryAnalyzer = Retry.class, priority = 1)
    public void ValidateLogin()
    {
        lp.loginWithCredentials("bheeshmacharya@gmail.com","Bheeshm@123");
        lp.clickOnloginButton();
        String errormsg = lp.ValidateError();
        Assert.assertEquals("Incorrect email or password.",errormsg);
    }
    @Test(groups = {"purchase"})
    public void End2End() throws IOException, InterruptedException {
        lp.loginWithCredentials("bheeshmacharya@gmail.com","Bheeshma@123");
        pc =lp.clickOnloginButton();
        //lp is in BaseTest class
        needItems= Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL");
        pc.ClickOnSelectedItems(needItems);
        //check if the selected items are present inside the cart
        cP=pc.clickOnCart();//in AbstractClass because we can use this cart in any page & all the pages inherit abstract class
        boolean areEqual = cP.verifyselecteditems(needItems);
        Assert.assertTrue(areEqual);
        Op = cP.clickOnCheckout();
        //select country and place order
        Op.selectCountry();
        Op.clickonPlaceOrder();
    }

}
