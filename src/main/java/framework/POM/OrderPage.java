package framework.POM;

import framework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver)
    {
        super(driver);
        //Initialix=zation
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    //PageFactory for POM
    @FindBy(css ="td:nth-child(3)")
    private List<WebElement> productNames;



    public Boolean VerifyOrderDisplay(String productname)
    {
        Boolean match = productNames.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));
        return match;
    }





}
