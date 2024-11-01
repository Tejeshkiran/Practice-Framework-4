package PageObjects;

import ReUsableCode.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderHystoryPage extends AbstractClass {
    WebDriver driver;
    public OrderHystoryPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //tr td:nth-child(3)
    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> OrderNameelemet;

    public boolean validateOrderHystory(List<String> itemtoValidate)
    {
      List<String> actualitems= OrderNameelemet.stream().map(p->p.getText()).collect(Collectors.toList());
      for(String st :itemtoValidate)
      {
          if(actualitems.contains(st))
          {
              return true;
          }
      }
        return false;
    }

}
