package Tests;

import DataPac.JsonToListofHashMap;
import PageObjects.*;
import TestComponents.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class End2EndTestCase extends BaseTest {
    public WebDriver driver;
    WebDriverWait w;
    List<String> needItems;
    JavascriptExecutor js;
    ProductCatalog pc;
    CartPage cP;
    OrderPage Op;

    @Test(dataProvider = "getdata",groups = {"purchase"})
    public void End2End(HashMap<String, String> input) throws IOException, InterruptedException {
        lp.loginWithCredentials(input.get("UserName"),input.get("Password"));
        pc =lp.clickOnloginButton();
        //lp is in BaseTest class
        needItems= Arrays.asList(input.get("Product1"),input.get("Product2"));
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
    @Test(dependsOnMethods ="End2End")
    public void OrderHystory() throws InterruptedException {
        lp.loginWithCredentials("bheeshmacharya@gmail.com","Bheeshma@123");
        pc =lp.clickOnloginButton();
        List<String> ItemtoValidate = Arrays.asList("ZARA COAT 3");
        OrderHystoryPage Ohp =lp.gotoOrderHystoryPage();//in AbstractClass
        Boolean result =Ohp.validateOrderHystory(ItemtoValidate);
        Assert.assertTrue(result);
    }

    /* @DataProvider
    public Object[][] getdata()
    {
        return new Object[][] {{"bheeshmacharya@gmail.com","Bheeshma@123"},
                {"Karna@gmail.com","Bheeshma@123"}};
    }*/
    @DataProvider
    public Object[][] getdata() throws IOException {
       /* HashMap<String,String> map1 = new HashMap<String, String>();
        map1.put("UserName","bheeshmacharya@gmail.com");
        map1.put("Password","Bheeshma@123");
        map1.put("Product1","ZARA COAT 3");
        map1.put("Product2","ADIDAS ORIGINAL");
        HashMap<String,String> map2 = new HashMap<String, String>();
        map2.put("UserName","Karna@gmail.com");
        map2.put("Password","Bheeshma@123");
        map2.put("Product1","ZARA COAT 3");
        map2.put("Product2","IPHONE 13 PRO");
        return new Object[][] {{map1},{map2}};*/
        JsonToListofHashMap ref = new JsonToListofHashMap();
        List<HashMap<String, String>> list = ref.dataReader(System.getProperty("user.dir")+
                "//src//main//java//DataPac//data.json");
        return new Object[][] {{list.get(0)},{list.get(1)}};
    }


}
