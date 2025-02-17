package organisation.eCommerce.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class E2ETest {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //implicitly wait
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.id("userEmail")).sendKeys("Selenium.user@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Password@123");
        driver.findElement(By.name("login")).click();

        List<WebElement> produtsList = driver.findElements(By.className("mb-3"));

        for (int i=0 ; i<produtsList.size(); i++) {
            String productName = produtsList.get(i).findElement(By.tagName("b")).getText();
            System.out.println(productName);
            if (productName.contains("ADIDAS ORIGINAL")) {
                produtsList.get(i).findElements(By.xpath("//button[contains(@class,'w-10')]")).get(i).click();
                WebElement successPopUp = driver.findElement(By.xpath("//div[contains(@class,'toast-success')]"));
                wait.until(ExpectedConditions.visibilityOf(successPopUp));
                wait.until(ExpectedConditions.invisibilityOf(successPopUp));
            }
        }
        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
        List<WebElement> cartList = driver.findElements(By.xpath("//div[@class='cartSection']//h3"));

        for (WebElement cartItem : cartList) {
            boolean matched = cartItem.getText().equalsIgnoreCase("ADIDAS ORIGINAL");
            Assert.assertTrue(matched);
        }
        driver.findElement(By.xpath("//button[text()='Checkout']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
        WebElement dropDown = driver.findElement(By.className("list-group"));
        wait.until(ExpectedConditions.visibilityOf(dropDown));
        List<WebElement> dropDownList = driver.findElements(By.className("list-group-item"));
        dropDownList.stream().filter(option -> option.getText().equalsIgnoreCase("India")).findFirst().get().click();
        driver.findElement(By.className("action__submit")).click();
        String actualSuccessMessage = driver.findElement(By.className("hero-primary")).getText().trim().toLowerCase();
        String expectedSuccessMessage = "Thankyou for the order.";
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage.toLowerCase());
        driver.findElement(By.xpath("//*[text()=' Sign Out ']")).click();
        driver.quit();
    }
}
