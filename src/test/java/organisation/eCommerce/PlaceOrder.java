package organisation.eCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import organisation.eCommerce.pageObjects.*;

import java.util.concurrent.TimeUnit;

public class PlaceOrder {
    public static void main(String[] args) {
        String input = "ADIDAS ORIGINAL";
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //implicitly wait
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.launchUrl();
        loginPage.login("Selenium.user@gmail.com", "Password@123");
        HomePage homePage = new HomePage(driver);
        homePage.addProductToCart(input);
        homePage.goToCart();
        CartPage cartPage = new CartPage(driver);
        boolean matched = cartPage.verifyProduct(input);
        Assert.assertTrue(matched);

        cartPage.checkOutProduct();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.selectCoutry("India");
        paymentPage.placeOrder();

        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        Assert.assertEquals(confirmationPage.getCofirmationMessage(), "Thankyou for the order.".toLowerCase());

        new LogoutPage(driver).signOut();

        driver.quit();
    }
}
