package framework.POM;

import framework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        //Initialix=zation
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    //PageFactory for POM
    @FindBy(xpath ="//*[@class=\"totalRow\"]/button")
    WebElement totalrow;

    @FindBy(xpath ="//input[@placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
    WebElement selectIndia;

    @FindBy(xpath ="//a[@class='btnn action__submit ng-star-inserted']")
    WebElement submit;

    By results = By.cssSelector(".ta-results");

    public void SelectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(country,countryName).build().perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitForElementToAppear(results);
        selectIndia.click();
    }

    public confirmationPage SubmitOrder()
    {
        submit.click();
        confirmationPage confirm = new confirmationPage(driver);
        return confirm;
    }




}
