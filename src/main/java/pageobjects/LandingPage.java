package pageobjects;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
//    Constructor
    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

//        PageFactory
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;

    public ProductCatalogue loginApplication(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo() {

        driver.get("https://rahulshettyacademy.com/client");
    }

    public RegisterForm goToRegisterForm() {

        driver.findElement(By.cssSelector("a[class='btn1']")).click();
        RegisterForm registerForm = new RegisterForm(driver);
        return registerForm;
    }

    public String getErrorMessage() {

        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
