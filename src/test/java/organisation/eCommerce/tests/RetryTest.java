package organisation.eCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import organisation.eCommerce.base.BaseTest;
import organisation.eCommerce.base.Iretry;

public class RetryTest extends BaseTest {
    @Test(retryAnalyzer = Iretry.class)
    public void retryAnalyzerTest() {
        loginPage.login("incorrectEmail@gmail.com","incorrectPassword");
        String errorMessage = loginPage.getLoginErrorMessage();
        System.out.println("incorrect Email: " + errorMessage);
        Assert.assertEquals(errorMessage, "Incorrect email / password.");
    }
}
