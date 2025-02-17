package organisation.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import organisation.eCommerce.utils.CommonUtils;

import java.util.List;

public class CartPage extends CommonUtils {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='cartSection']//h3")
    List<WebElement> cartList;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkOut;

    public List<WebElement> getCartList() {
        waitUntilElementVisible(cartList);
        return cartList;
    }

    public boolean verifyProduct(String inputName) {
        boolean matched = false;
        for (WebElement cartItem : getCartList()) {
            matched = cartItem.getText().equalsIgnoreCase(inputName);
        }
        return matched;
    }

    public PaymentPage checkOutProduct() {
        checkOut.click();
        PaymentPage paymentPage = new PaymentPage(driver);
        return paymentPage;
    }
}
