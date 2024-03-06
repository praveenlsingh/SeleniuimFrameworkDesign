package framework.AbstractComponents;

import framework.POM.CartPage;
import framework.POM.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    public AbstractComponent(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cartHeader;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement Orders;

    public void waitForElementToAppear(By findBy)
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

        }

    public void waitForWebElementToAppear(WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public CartPage goToCartPage()
        {
            cartHeader.click();
            CartPage cartPage = new CartPage(driver);
            return cartPage;
        }

    public OrderPage goToOrders()
    {
        Orders.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

    public void waitForElementToDisappear(WebElement ele) {
        //Thread.sleep(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until((ExpectedConditions.invisibilityOf(ele)));
    }
}
