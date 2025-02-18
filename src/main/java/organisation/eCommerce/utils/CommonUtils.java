package organisation.eCommerce.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import organisation.eCommerce.pageObjects.CartPage;
import organisation.eCommerce.pageObjects.OrderHistoryPage;

import java.util.List;

public class CommonUtils {
    WebDriver driver;
    WebDriverWait wait;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cart;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement orders;

    public void waitUntilElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitUntilElementInVisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void waitUntilElementIsClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public CartPage goToCart()
    {
        waitUntilElementIsClickable(cart);
        cart.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public OrderHistoryPage goToOrders(){
        orders.click();
        OrderHistoryPage orderHistoryPageObj = new OrderHistoryPage(driver);
        return orderHistoryPageObj;
    }
}
