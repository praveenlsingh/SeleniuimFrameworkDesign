package framework.POM;

import framework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver)
    {
        super(driver);
        //Initialix=zation
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    //PageFactory for POM
    @FindBy(xpath ="//*[@class='cartSection']/h3")
    List<WebElement> cartproduct;

    @FindBy(xpath="//*[@class=\"totalRow\"]/button" )
    WebElement checkoutEle;

    public Boolean VerifyProductDisplay(String productname)
    {
        Boolean match = cartproduct.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));
        return match;
    }

    public CheckoutPage goToCheckout()
    {
        checkoutEle.click();
        CheckoutPage checkout =new CheckoutPage(driver);
        return checkout;
    }



}
