package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;

    private By loginField = By.id("username");
    private By passwordField = By.id("password");
    private By submitButton = By.xpath("//button[@data-test='login-button']");
    private By userProfile = By.xpath("//div[@data-test='ring-dropdown ring-profile']");
    private By logoutButton = By.xpath("//button[text()='Выйти']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        WaitUtils.waitForPageLoadComplete(driver);
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public String getProfileTitle() {
        return driver.findElement(userProfile).getDomAttribute("title");
    }

    public void logout() {
        MenuPage profile = new MenuPage(driver);
        profile.openProfile();
        WaitUtils.waitForElementClickable(driver, logoutButton).click();
    }

    public boolean isSubmitBtnVisible() {
        return driver.findElement(submitButton).isDisplayed();
    }
}