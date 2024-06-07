package pageobjects;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentAndShipping extends AbstractComponent {

    WebDriver driver;

    public PaymentAndShipping(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = ".user__address input ")
    WebElement dropdown;

    @FindBy(css = ".ta-results button")
    List<WebElement> options;

    @FindBy(css = ".action__submit")
    WebElement placeOrderButton;

    By dropdownResults = By.cssSelector(".ta-results");
    public void selectCountry(String country) {

        dropdown.sendKeys(country);
        waitForElementToAppear(dropdownResults);

//        Handle Auto-suggest search dropdown

        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(country)) {
                option.click();
                break;
            }
        }
    }

    public ConfirmationPage submitOrder() {

        Actions action = new Actions(driver);
        action.moveToElement(placeOrderButton).click().build().perform();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }






}
