package organisation.eCommerce.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import organisation.eCommerce.utils.CommonUtils;

import java.util.List;

public class HomePage extends CommonUtils {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "mb-3")
    List<WebElement> produtsList;
    @FindBy(xpath = "//button[contains(@class,'w-10')]")
    List<WebElement> addToCart;
    @FindBy(xpath = "//div[contains(@class,'toast-success')]")
    WebElement productAddedConfirmation;

    @FindBy(xpath = "//div[contains(@class,'ngx-spinner-overlay')]")
     WebElement spinner;
    By productText = By.tagName("b");

    public List<WebElement> getProductsList() {
        waitUntilElementVisible(produtsList);
        return produtsList;
    }


    public void addProductToCart(String inputName) {

        for (int i = 0; i < getProductsList().size(); i++) {
            String productName = getProductsList().get(i).findElement(productText).getText();
            if (productName.contains(inputName)) {
                //getProductsList().get(i).addToCart.get(i).click();
                addToCart.get(i).click();
                //waitUntilElementInVisible(spinner);
                waitUntilElementVisible(productAddedConfirmation);
            }
        }
    }
}
