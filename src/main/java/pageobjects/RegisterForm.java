package pageobjects;

import abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterForm  extends AbstractComponent {


    WebDriver driver;

    public RegisterForm(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(id = "firstName")
    WebElement firstname;

    @FindBy(id = "lastName")
    WebElement lastname;

    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(id = "userMobile")
    WebElement userMobile;

    @FindBy(id = "confirmPassword")
    WebElement confirmPassword;

    @FindBy(tagName = "select")
    WebElement dropdown;

    @FindBy(id = "login")
    WebElement registerButton;

    @FindBy(xpath = "//div[@class='col-md-1']/input")
    WebElement comingOfAge;


    public void registerApplication(String firstName, String lastName, String email, String number, String password) {

        firstname.sendKeys(firstName);
        lastname.sendKeys(lastName);
        userEmail.sendKeys(email);
        userMobile.sendKeys(number);
        userPassword.sendKeys(password);
        confirmPassword.sendKeys(password);
    }



    public void selectOccupation(String occupation) {

        Select occupationDropDown = new Select(dropdown);

        occupationDropDown.selectByVisibleText(capitalization(occupation));

    }

    public void selectGender(String gender){

        driver.findElement(By.cssSelector("input[value='" + capitalization(gender) + "']")).click();
    }

    public void registerConfirmation() {

        registerButton.click();
        comingOfAge.click();
    }

    public LandingPage continueToLogIn() {

        driver.findElement(By.cssSelector("a[class='text-reset']")).click();
        LandingPage landingPage = new LandingPage(driver);
        return landingPage;
    }

}
