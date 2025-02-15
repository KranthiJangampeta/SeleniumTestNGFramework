package organisation.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import organisation.eCommerce.utils.CommonUtils;

import java.util.List;

public class PaymentPage extends CommonUtils { // forth step
    WebDriver driver; // first step

    public PaymentPage(WebDriver driver) {  // second step
        super(driver); // fifth step
        this.driver = driver;
        PageFactory.initElements(driver, this); // third step
    }

    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement countryField;
    @FindBy(className = "list-group")
    WebElement countryDropdown;
    @FindBy(className = "list-group-item")
    List<WebElement> countryList;
    @FindBy(className = "action__submit")
    WebElement placeOrder;

    public void selectCoutry(String countryName) {
        countryField.sendKeys(countryName);
        waitUntilElementVisible(countryDropdown);
        countryList.stream().filter(option ->
                option.getText().equalsIgnoreCase(countryName)).findFirst().get().click();
    }

    public void placeOrder() {
        placeOrder.click();
    }
}
