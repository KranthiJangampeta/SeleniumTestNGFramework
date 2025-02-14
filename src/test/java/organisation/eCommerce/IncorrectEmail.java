package organisation.eCommerce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import organisation.eCommerce.pageObjects.LoginPage;

import java.util.concurrent.TimeUnit;

public class IncorrectEmail {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //implicitly wait
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.launchUrl();
        loginPage.login("incorrectemail", "Password@123");

    }
}
