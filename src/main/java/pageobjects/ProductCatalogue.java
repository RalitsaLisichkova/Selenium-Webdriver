package pageobjects;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue  extends AbstractComponent {

    WebDriver driver;
    public ProductCatalogue(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".col-lg-4")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement load;

    @FindBy(id = "toast-container")
    WebElement message;

    By productsLocator = By.cssSelector(".col-lg-4");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.id("toast-container");




    public List<WebElement> getProductList() {
        waitForElementToAppear(productsLocator);
        return products;
    }

    public WebElement getProductByName (String productName) {

        WebElement product =  getProductList().stream().filter(item->
                item.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
        return product;
    }
    public void addProductToCart(String productName) {

        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();

        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(load);
        waitForElementToDisappear(message);
    }

}
