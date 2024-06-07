package abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CartPage;
import pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {

        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='dashboard/cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='/dashboard/myorders']")
    WebElement orderHeader;


    By cart = By.xpath("//ul/li[4]");

//    Reusable methods
    public void waitForElementToAppear(By findBy) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }

    public void waitForWebElementToAppear(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(webElement));

    }

    public void waitForElementToDisappear(WebElement webElement) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(webElement));

    }

    public CartPage goToCartPage() {

        waitForElementToAppear(cart);
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrderPage() {

        waitForElementToAppear(cart);
        orderHeader.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }
    public String capitalization(String string) {

        String s = string.toLowerCase();
        String string1 = s.substring(0, 1).toUpperCase() + s.substring(1);
        return string1;
    }

}
