package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import pages.IssuePage;
import pages.LoginPage;
import pages.MenuPage;
import utils.BrowserUtils;
import utils.ExcelReader;
import utils.ScreenshotOnFailureExtension;

import java.time.Duration;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

public class BaseTest {
    private static final Logger logger = getLogger(BaseTest.class);
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected LoginPage loginPage;
    protected MenuPage menuPage;

    @BeforeAll
    public static void setupDriverManager() {
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.edgedriver().setup();
    }

    @RegisterExtension
    ScreenshotOnFailureExtension screenshotExtension = new ScreenshotOnFailureExtension();

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        //EdgeOptions edgeOptions = new EdgeOptions();
        //edgeOptions.addArguments("--inprivate");
        //driver = new EdgeDriver(edgeOptions);

        //driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        screenshotExtension.setDriver(driver);

        loginPage = new LoginPage(driver, wait);
        menuPage = new MenuPage(driver);
        driver.get("https://didzi.youtrack.cloud");
        BrowserUtils.refreshIfMainElementMissing(driver, By.xpath("//button[@type='submit']"));

        if (shouldLogin()) {
            loginPage.login("admin", "sa85BqdhWn3UeHp");
        }
    }

    protected boolean shouldLogin() {
        return true;
    }

    protected static Stream<String> readProjectNames() throws Exception {
        return ExcelReader.readNames("src/test/resources/testdata/project_names.xlsx").stream();
    }

    protected static Stream<String> readTaskNames() throws Exception {
        return ExcelReader.readNames("src/test/resources/testdata/task_names.xlsx").stream();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Драйвер завершён после теста");
        }
    }

}