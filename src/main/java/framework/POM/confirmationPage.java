package framework.POM;

import framework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class confirmationPage extends AbstractComponent {
    WebDriver driver;

    public confirmationPage(WebDriver driver)
    {
        super(driver);
        //Initialix=zation
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    //PageFactory for POM
    @FindBy(xpath ="//h1[@class='hero-primary']")
    WebElement order;

    public String getconfirmationmessage()
    {
        String validateMessage = order.getText();
        return validateMessage;


    }



}
