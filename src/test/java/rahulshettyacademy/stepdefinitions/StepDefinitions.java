package rahulshettyacademy.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.*;
import rahulshettyacademy.TestComponents.BaseTest;

import java.io.IOException;

public class StepDefinitions extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalogue productCatalogue;
    public CartPage cartPage;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void iLandedOnEcommercePage() throws IOException {

       landingPage = launchApplication();

    }

    @Given("Login with valid credentials - {} and {}")
    public void loginWithValidCredentialsAnd(String username, String password) {

        productCatalogue = landingPage.loginApplication(username, password);
    }

    @When("I add a product {} to Cart")
    public void iAddAProductToCart(String productName) {

        productCatalogue.getProductByName(productName);
        productCatalogue.addProductToCart(productName);
    }

    @And("Checkout {}")
    public void checkout(String productName) {

        cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
    }

    @And("I submit the order")
    public void iSubmitTheOrder() {

        PaymentAndShipping paymentAndShipping = cartPage.goTocheckOut();
        paymentAndShipping.selectCountry("Bulgaria");
        confirmationPage = paymentAndShipping.submitOrder();
    }

    @Then("Verify {} is displayed on confirmation page")
    public void verifyIsDisplayedOnConfirmationPage(String message) {

        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(actualMessage.equalsIgnoreCase(message));
        driver.quit();
    }

    @When("Login with invalid credentials - {} and {}")
    public void loginWithInvalidCredentialsAnd(String username, String password) {

        productCatalogue = landingPage.loginApplication(username, password);
    }

    @Then("Verify error {} is displayed")
    public void verifyErrorIsDisplayed(String message) {

        String actualMessage = landingPage.getErrorMessage();
        Assert.assertEquals(actualMessage, message);
        driver.quit();
    }
}
