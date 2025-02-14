package organisation.eCommerce.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userPassword")
    WebElement userPassword;
    @FindBy(name = "login")
    WebElement loginButton;

    public void login(String userName, String password) {
        userEmail.sendKeys(userName);
        userPassword.sendKeys(password);
        loginButton.click();
    }

    public void launchUrl() {
        driver.get("https://rahulshettyacademy.com/client");
    }
}
