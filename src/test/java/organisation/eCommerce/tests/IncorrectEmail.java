package organisation.eCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import organisation.eCommerce.base.BaseTest;

public class IncorrectEmail extends BaseTest {

    @Test
    public void incorrectEmailValidation() {
        loginPage.login("inCorrectMail@gmail.com", "Password@123");
        String errorMessage = loginPage.getLoginErrorMessage();
        System.out.println("incorrect Email: " + errorMessage);
        Assert.assertEquals(errorMessage, "Incorrect email or password.");
    }
    @Test
    public void incorrectPasswordValidation() {
        loginPage.login("Selenium.user@gmail.com", "Password@12");
        String errorMessage = loginPage.getLoginErrorMessage();
        System.out.println("incorrect Password: " + errorMessage);
        Assert.assertEquals(errorMessage, "Incorrect email or password.");
    }
}
