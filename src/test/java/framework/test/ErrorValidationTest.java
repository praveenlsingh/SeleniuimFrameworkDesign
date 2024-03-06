package framework.test;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import framework.POM.CartPage;
import framework.POM.CheckoutPage;
import framework.POM.confirmationPage;
import framework.POM.productCatalog;
import framework.TestComponents.BaseTest;
import framework.TestComponents.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTest {

        @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)   //Code to rerun test on failure
        public void LoginErrorValidation() throws IOException {
        String productname = "ZARA COAT 3";
        //LandingPage landingPage = launchApplication();

        landingPage.loginApplication("praveenlsingh@gmail.com","Test@123456");
        Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());
    }

        @Test
        public void ProductErrorValidation() throws IOException {
                String productname = "ZARA COAT 3";
                //LandingPage landingPage = launchApplication();
                productCatalog objproductCatalog = landingPage.loginApplication("praveenlsingh@gmail.com","Test@12345");
                //productCatalog objproductCatalog = new productCatalog(driver); //replaced by above step to save object creation
                List <WebElement> products = objproductCatalog.getproductList();
                objproductCatalog.addProductToCart(productname);
                CartPage cartPage = objproductCatalog.goToCartPage();
                //CartPage cartPage = new CartPage(driver); //replaced by above step to save object creation
                Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
                Assert.assertFalse(match);
        }

}
