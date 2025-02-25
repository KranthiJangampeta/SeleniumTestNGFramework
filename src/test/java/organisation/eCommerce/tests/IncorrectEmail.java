package organisation.eCommerce.tests;

import com.sun.net.httpserver.Authenticator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import organisation.eCommerce.base.BaseTest;
import organisation.eCommerce.base.Iretry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class IncorrectEmail extends BaseTest {

    @Test(dataProvider = "getDataFromIncorrectCredentails")
    public void incorrectCredentialsValidation(HashMap<String,String> hm) {
        loginPage.login(hm.get("email"), hm.get("password"));
        String errorMessage = loginPage.getLoginErrorMessage();
        System.out.println("incorrect Email: " + errorMessage);
        Assert.assertEquals(errorMessage, "Incorrect email / password.");
    }

/*    @Test
    public void incorrectPasswordValidation() {
        loginPage.login("Selenium.user@gmail.com", "Password@12");
        String errorMessage = loginPage.getLoginErrorMessage();
        System.out.println("incorrect Password: " + errorMessage);
        Assert.assertEquals(errorMessage, "Incorrect email or password.");
    }*/

    @DataProvider
    public Object[][] getDataFromIncorrectCredentails() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap("IncorrectCredentials");
        Object[][] ob = new Object[][]{{data.get(0)}, {data.get(1)}};
        return ob;
    }
}
