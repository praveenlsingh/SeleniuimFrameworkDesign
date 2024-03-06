package framework.test;

import framework.POM.*;
import framework.TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
        String productname = "ZARA COAT 3";

        @Test (dataProvider = "getData",groups = "purchase")
        //public void SubmitOrder(String email, String password, String productname) throws IOException {
        public void SubmitOrder(HashMap<String,String> input) throws IOException {
        //LandingPage landingPage = launchApplication();
        productCatalog objproductCatalog = landingPage.loginApplication(input.get("email"),input.get("password"));
        //productCatalog objproductCatalog = new productCatalog(driver); //replaced by above step to save object creation
        List <WebElement> products = objproductCatalog.getproductList();
        objproductCatalog.addProductToCart(input.get("product"));
        CartPage cartPage = objproductCatalog.goToCartPage();
        //CartPage cartPage = new CartPage(driver); //replaced by above step to save object creation
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkout = cartPage.goToCheckout();
        checkout.SelectCountry("India");
        confirmationPage confirm = checkout.SubmitOrder();
        String confirmMessage = confirm.getconfirmationmessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println(confirmMessage);
    }

    //To verify Zara Coat 3 is visible in Order page
        @Test(dependsOnMethods = {"SubmitOrder"})
        public void OrderHistoryTest()
        {
            productCatalog objproductCatalog = landingPage.loginApplication("praveenlsingh@gmail.com","Test@12345");
            OrderPage orderPage = objproductCatalog.goToOrders();
            Assert.assertTrue(orderPage.VerifyOrderDisplay(productname));
        }


        @DataProvider
        public Object[][] getData() throws IOException {
            List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//framework//data//PurchaseOrder.json");
            return new Object[][] {{data.get(0)},{data.get(1)}};
        }


//    @DataProvider
//    public Object[][] getData()
//    {
//        HashMap<String,String> map = new HashMap<String,String>();
//        map.put("email","praveenlsingh@gmail.com");
//        map.put("password","Test@12345");
//        map.put("product","ZARA COAT 3");
//
//        HashMap<String,String> map1 = new HashMap<String,String>();
//        map1.put("email","praveenlsingh@gmail.com");
//        map1.put("password","Test@12345");
//        map1.put("product","ADIDAS ORIGINAL");
//        return new Object[][] {{map},{map1}};
//    }


//    @DataProvider
//    public Object[][] getData()
//    {
//        //return new Object[][] {{"praveenlsingh@gmail.com","Test@12345","ZARA COAT 3"},{"praveenlsingh@gmail.com","Test@12345","ADIDAS ORIGINAL"}};
//    }



}
