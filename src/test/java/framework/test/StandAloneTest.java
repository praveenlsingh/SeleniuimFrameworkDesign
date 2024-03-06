package framework.test;

import framework.POM.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        String productname = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
       driver.get("https://rahulshettyacademy.com/client/");
        LandingPage landingPage = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("praveenlsingh@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@12345");
        driver.findElement(By.id("login")).click();
        //Wait
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        // wait until the class is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod = products.stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //Wait
        // wait until the class is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating, wait until the class is invisible
        //wait.until((ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))));
        wait.until((ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[contains(@class,'ng-animating')]")))));

      /*  //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        */


        //Click on Cart
        //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
            driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();


        List<WebElement> cartproduct = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));

        Boolean match = cartproduct.stream().anyMatch(cart->cart.getText().equalsIgnoreCase(productname));

        Assert.assertTrue(match);
        driver.findElement(By.xpath("//*[@class=\"totalRow\"]/button")).click();

        //
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
        //driver.findElement(By.xpath("//input[@placeholder='Select Country']")).click();
        //driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
        driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
        String validateMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
        Assert.assertTrue(validateMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));



    }
}
