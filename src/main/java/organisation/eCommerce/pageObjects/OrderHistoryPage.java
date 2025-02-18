package organisation.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import organisation.eCommerce.utils.CommonUtils;

public class OrderHistoryPage extends CommonUtils { // forth step
    WebDriver driver; // first step

    public OrderHistoryPage(WebDriver driver) {  // second step
        super(driver); // fifth step
        this.driver = driver;
        PageFactory.initElements(driver, this); // third step
    }

    @FindBy(xpath = "//tr[@class='ng-star-inserted']/td[2]")
    WebElement productName;

    public boolean verifyProduct(String expected) {
        String actual = productName.getText();
        if (actual.contains(expected)) {
            return true;
        } else return false;

    }

}
