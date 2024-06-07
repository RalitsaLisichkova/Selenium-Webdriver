package pageobjects;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    public CartPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(xpath = "//button[text()=\"Checkout\"]")
    WebElement checkout;

    @FindBy(css = ".btn-primary[routerlink*='/dashboard']")
    WebElement continueShopping;

    By myCartHeading = By.tagName("h1");



    //    Check if our product is in shopping cart
    public boolean verifyProductDisplay(String productName) {

        waitForElementToAppear(myCartHeading);
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public PaymentAndShipping goTocheckOut() {

        checkout.click();
        PaymentAndShipping paymentAndShipping = new PaymentAndShipping(driver);
        return paymentAndShipping;
    }

    public void continueShopping() {

        continueShopping.click();
    }
}
