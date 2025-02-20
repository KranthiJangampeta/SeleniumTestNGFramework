package organisation.eCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import organisation.eCommerce.base.BaseTest;
import organisation.eCommerce.pageObjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PlaceOrder extends BaseTest {
    String input = "ADIDAS ORIGINAL";

    @Test(dataProvider = "getDataForSubmitProduct")
    public void submitProduct(HashMap<String, String> hm) throws IOException, InterruptedException {

        HomePage homePage = loginPage.login(hm.get("email"), hm.get("password"));
        homePage.addProductToCart(hm.get("product"));
        CartPage cartPage = homePage.goToCart();
        boolean matched = cartPage.verifyProduct(hm.get("product"));
        Assert.assertTrue(matched);

        PaymentPage paymentPage = cartPage.checkOutProduct();
        // PaymentPage paymentPage = new PaymentPage(driver); created object after clicking checkout and returned paymenat page Object

        paymentPage.selectCoutry("India");
        ConfirmationPage confirmationPage = paymentPage.placeOrder();
        //ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        Assert.assertEquals(confirmationPage.getCofirmationMessage(), "Thankyou for the order.".toLowerCase());
        Thread.sleep(3000);
        new LogoutPage(driver).signOut();
    }

    @Test(dependsOnMethods = {"submitProduct"})
    public void verifyProductInOrderHistoryPage() {
        HomePage homePage = loginPage.login("Selenium.user@gmail.com", "Password@123");
        OrderHistoryPage orderHistoryPage = homePage.goToOrders();
        boolean status = orderHistoryPage.verifyProduct(input);
        Assert.assertTrue(status);
    }

    @DataProvider
    public Object[][] getDataForSubmitProduct() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap();
        Object[][] obj = new Object[][]{{data.get(0)}, {data.get(1)}, {data.get(2)}};
        return obj;
    }

/*    @DataProvider
    public Object[][] getDataForSubmitProduct() throws IOException {
        HashMap<String, String> hm1 = new HashMap<>();
        hm1.put("email", "Selenium.user@gmail.com");
        hm1.put("password", "Password@123");
        hm1.put("product", "ADIDAS ORIGINAL");

        HashMap<String, String> hm2 = new HashMap<>();
        hm2.put("email", "Selenium.user1@gmail.com");
        hm2.put("password", "Password@123");
        hm2.put("product", "ZARA COAT 3");

        HashMap<String, String> hm3 = new HashMap<>();
        hm3.put("email", "Selenium.user2@gmail.com");
        hm3.put("password", "Password@123");
        hm3.put("product", "ADIDAS ORIGINAL");

        Object[][] obj = new Object[][]{{hm1}, {hm2}, {hm3}};
        return obj;
    }
    */


   /* @DataProvider
    public Object[][] getDataForSubmitProduct(){
        Object[][] obj = new Object[][]{
                {"Selenium.user@gmail.com", "Password@123", "ADIDAS ORIGINAL", "Country"},
                {"Selenium.user1@gmail.com", "Password@123", "ZARA COAT 3"},
                {"Selenium.user2@gmail.com", "Password@123", "IPHONE 13 PRO"}
        };
        return obj;
    }*/
}
