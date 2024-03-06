package framework.POM;

import framework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        //Initialix=zation
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    //POM
    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //PageFactory for POM
    @FindBy(id ="userEmail")
    WebElement userEmail;

    @FindBy(id ="userPassword")
    WebElement password;

    @FindBy(id ="login")
    WebElement submit;

    //@FindBy(css="[class*='flyout']")
    @FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
    WebElement errorMessage;

    public productCatalog loginApplication(String user, String pass)
    {
        userEmail.sendKeys(user);
        password.sendKeys(pass);
        submit.click();
        productCatalog objproductCatalog = new productCatalog(driver);
        return objproductCatalog;

    }

    public String getErrorMessage()
    {

        waitForWebElementToAppear( errorMessage);
        return errorMessage.getText();
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }


}
