package pageobjects;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> ordersNameList;

    By name = By.cssSelector("td:nth-child(3)");
    @FindBy(css = "tr.ng-star-inserted")
    List<WebElement> orders;

    @FindBy(xpath = "//div[@aria-label='Orders Deleted Successfully']")
    WebElement deleteMessage;


    By deleteButton = By.cssSelector(".btn-danger");


    public boolean verifyOrderByName(String orderName) {

       boolean match = ordersNameList.stream().anyMatch(s-> s.getText().equalsIgnoreCase(orderName));
       return match;
    }

    public void deleteOrder(String orderName) {

        WebElement selectedOrder = orders.stream().filter(s->s.findElement(name).getText().contains(orderName)).findFirst().get();
        selectedOrder.findElement(deleteButton).click();
    }

    public String deleteMessageDisplay() {

        return deleteMessage.getText();
    }

}
