package organisation.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import organisation.eCommerce.utils.CommonUtils;

public class LogoutPage extends CommonUtils {
    WebDriver driver;

    public LogoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[text()=' Sign Out ']")
    WebElement signOut;

    public void signOut() {
        signOut.click();
    }
}
