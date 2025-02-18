package organisation.eCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import organisation.eCommerce.base.BaseTest;
import organisation.eCommerce.pageObjects.*;

import java.io.IOException;

public class PlaceOrder extends BaseTest {
    String input = "ADIDAS ORIGINAL";
    @Test(dataProvider = "getDataForSubmitProduct")
    public void submitProduct(String userName, String password, String product ) throws IOException, InterruptedException {

        HomePage homePage = loginPage.login(userName, password);
        homePage.addProductToCart(product);
        CartPage cartPage = homePage.goToCart();
        boolean matched = cartPage.verifyProduct(product);
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
    public void verifyProductInOrderHistoryPage(){
        HomePage homePage = loginPage.login("Selenium.user@gmail.com", "Password@123");
        OrderHistoryPage orderHistoryPage = homePage.goToOrders();
        boolean status = orderHistoryPage.verifyProduct(input);
        Assert.assertTrue(status);
    }
    @DataProvider
    public Object[][] getDataForSubmitProduct(){
        Object[][] obj = new Object[][]{
                {"Selenium.user@gmail.com", "Password@123", "ADIDAS ORIGINAL"},
                {"Selenium.user1@gmail.com", "Password@123", "ZARA COAT 3"},
                {"Selenium.user2@gmail.com", "Password@123", "IPHONE 13 PRO"}
        };
        return obj;
    }
}
