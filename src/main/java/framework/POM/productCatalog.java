package framework.POM;

import framework.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class productCatalog extends AbstractComponent {
    WebDriver driver;
    public productCatalog(WebDriver driver)
    {
        //Initialization
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //POM
    //WebElement userEmail = driver.findElement(By.id("userEmail"));

    //PageFactory for POM
    @FindBy(css =".mb-3")
    List<WebElement> products;

    @FindBy(xpath="//div[contains(@class,'ng-animating')]")
    WebElement spinner;

    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement cart;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getproductList()
    {
        waitForElementToAppear(productsBy);
        return products;
        //WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        //prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    }

    public WebElement getProductName(String productname)
    {
        WebElement prod = getproductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        return prod;
    }

    public void addProductToCart(String productname)
    {
        WebElement prod = getProductName(productname);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }


}
