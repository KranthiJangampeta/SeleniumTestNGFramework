package organisation.eCommerce.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import organisation.eCommerce.base.BaseTest;
import organisation.eCommerce.pageObjects.*;

import java.io.IOException;

public class PlaceOrder extends BaseTest {
    @Test
    public void submitProduct() throws IOException {
        String input = "ADIDAS ORIGINAL";

        HomePage homePage = loginPage.login("Selenium.user@gmail.com", "Password@123");
        homePage.addProductToCart(input);
        CartPage cartPage = homePage.goToCart();
        boolean matched = cartPage.verifyProduct(input);
        Assert.assertTrue(matched);

        PaymentPage paymentPage = cartPage.checkOutProduct();
        // PaymentPage paymentPage = new PaymentPage(driver); created object after clicking checkout and returned paymenat page Object

        paymentPage.selectCoutry("India");
        ConfirmationPage confirmationPage = paymentPage.placeOrder();
        //ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        Assert.assertEquals(confirmationPage.getCofirmationMessage(), "Thankyou for the order.".toLowerCase());
        new LogoutPage(driver).signOut();
    }
}
