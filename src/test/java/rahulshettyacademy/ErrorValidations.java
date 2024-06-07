package rahulshettyacademy;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidations extends BaseTest {

    static String userEmail = "ralitsa@gmail.com";
    static String userPassword = "qaacademy123";
    static String productName = "IPHONE 13 PRO";
    static String country = "Australia";

@Test(retryAnalyzer = Retry.class)
    public void loginErrorValidation() {

//        Login Form
       landingPage.loginApplication(userEmail, userPassword);
       String actualMessage = landingPage.getErrorMessage();
       Assert.assertEquals(actualMessage, "Incorrect email or password.");

    }
}
