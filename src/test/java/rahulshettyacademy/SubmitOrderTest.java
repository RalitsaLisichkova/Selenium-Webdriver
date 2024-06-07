package rahulshettyacademy;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    static String firstName = "Ralitsa";
    static String lastName = "Lisichkova";
    static String number = "3988888888";
    static String userEmail = "ralitsalisichkova@gmail.com";
    static String gender = "female";
    static String occupation = "doctor";
    static String userPassword = "QAAcademy123";
    static String productName = "IPHONE 13 PRO";
    static String country = "Australia";



    @DataProvider
    public Object[][] getData() {

        HashMap<String, String> map = new HashMap<>();
        map.put("email", "ralitsalisichkova@gmail.com");
        map.put("password", "QAAcademy123");
        map.put("product", "IPHONE 13 PRO");
        map.put("country", "Bulgaria");
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("email", "peturpopov@gmail.com");
        map1.put("password", "Petur123");
        map1.put("product", "ADIDAS ORIGINAL");
        map1.put("country", "India");

        return new Object[][] {{map}, {map1}};
    }

@Test
    public void submitOrder() {

//              Register Form
        RegisterForm registerForm = landingPage.goToRegisterForm();

//       Give credentials
        registerForm.registerApplication(firstName,lastName,userEmail,number,userPassword);
        registerForm.selectOccupation(occupation);
        registerForm.selectGender(gender);

        registerForm.registerConfirmation();

//        Login Form
        registerForm.continueToLogIn();

//       Valid Credentials
        ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);


//        Products page --> Getting a product
        List<WebElement> products = productCatalogue.getProductList();

        productCatalogue.getProductByName(productName);

//        Adding to card
        productCatalogue.addProductToCart(productName);

//        Is toast container displayed on screen

//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
//        Animated icon for loading --> //ng-animating

//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

//        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));

        CartPage cartPage = productCatalogue.goToCartPage();
//

//        Check if our product is in shopping cart

        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

//        Checkout
        PaymentAndShipping paymentAndShipping = cartPage.goTocheckOut();


//        Shipping Information
        paymentAndShipping.selectCountry(country);
        ConfirmationPage confirmationPage = paymentAndShipping.submitOrder();


//        Is order successfully finished validation
        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(actualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dataProvider = "getData")
    public void orderProductTest(HashMap<String, String> input) {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        productCatalogue.getProductByName(input.get("product"));

        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();


        boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        PaymentAndShipping paymentAndShipping = cartPage.goTocheckOut();


        paymentAndShipping.selectCountry(input.get("country"));
        ConfirmationPage confirmationPage = paymentAndShipping.submitOrder();

        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(actualMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() {

        ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);
        OrderPage orderPage = productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderByName(productName));

    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void deleteOrderTest() {

        ProductCatalogue productCatalogue = landingPage.loginApplication(userEmail, userPassword);
        OrderPage orderPage = productCatalogue.goToOrderPage();
        orderPage.deleteOrder(productName);
        Assert.assertEquals(orderPage.deleteMessageDisplay(), "Orders Deleted Successfully");
    }

}
