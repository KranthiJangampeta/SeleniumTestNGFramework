package organisation.eCommerce.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import organisation.eCommerce.pageObjects.ConfirmationPage;
import organisation.eCommerce.pageObjects.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest extends DataReader {
    public WebDriver driver;
    public LoginPage loginPage;
    public ConfirmationPage confirmationPage;

    //@BeforeSuite
    public void driverInitialization() throws IOException {
        Properties properties = new Properties();
        System.out.println(System.getProperty("user.dir"));
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/Test.properties");
        properties.load(fileInputStream);
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            //System.setProperty("webdriver.edge.driver", "src/drivers/edgedriver.exe");
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        //implicitly wait
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
    }

    @BeforeMethod
    public void launchURL() throws IOException {
        driverInitialization();
        loginPage = new LoginPage(driver);
        confirmationPage = new ConfirmationPage(driver);
        loginPage.launchUrl();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.close();
        Thread.sleep(5000);
    }


    public ExtentReports extentReportConfiguration() {
        ExtentReports extentReportObj;
        String path = System.getProperty("user.dir") + "/extentReports/index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Regression results");
        reporter.config().setDocumentTitle("Air India");

        extentReportObj = new ExtentReports();
        extentReportObj.attachReporter(reporter);
        extentReportObj.setSystemInfo("Tester", "Lokesh");
        return extentReportObj;
    }

    public void takeScreenshot(WebDriver driver, String testCaseName ){

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

        File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/extentReports/" + testCaseName + ".png";
        File dst = new File(path);
        try {
            FileUtils.copyFile(src, dst);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
