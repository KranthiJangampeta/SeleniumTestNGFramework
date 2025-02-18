package organisation.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import organisation.eCommerce.utils.CommonUtils;

public class ConfirmationPage extends CommonUtils {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "hero-primary")
    WebElement successMessage;
    @FindBy(xpath = "//label[text()=' Orders History Page ']")
    WebElement orderHistoryPage;

    public String getCofirmationMessage() {
        return successMessage.getText().trim().toLowerCase();
    }
    public OrderHistoryPage goToOrderHistoryPage() {
        waitUntilElementVisible(orderHistoryPage);
        orderHistoryPage.click();
        OrderHistoryPage orderHistoryPageObj = new OrderHistoryPage(driver);
        return orderHistoryPageObj;
    }
}
