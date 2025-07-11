package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import utils.BrowserUtils;
import utils.WaitUtils;

import static org.slf4j.LoggerFactory.getLogger;
import static pages.AgileBoardsPage.agilePageHeader;
import static pages.ProjectsPage.newProjectButton;

public class MenuPage {
    private WebDriver driver;
    private static final Logger logger = getLogger(MenuPage.class);

    private By projects = By.xpath("//a[@href='projects']");
    private By agileBoards = By.xpath("//a[@href='agiles']");
    private By profile = By.xpath("//div[@data-test='ring-dropdown ring-profile']");

    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openProjectsPage() {
        int maxAttempts = 5;
        int attempts = 0;
        boolean success = false;
        BrowserUtils.refreshIfMainElementMissing(driver, newProjectButton);

        while (attempts < maxAttempts) {
            try {
                WaitUtils.waitForElementClickable(driver, projects).click();
                WaitUtils.waitForElementVisible(driver, newProjectButton);
                success = true;
                break;
            } catch (Exception e) {
                logger.info("Попытка " + (attempts + 1) + " не удалась: " + e.getMessage());
                attempts++;
            }
        }

        if (!success) {
            throw new RuntimeException("Элемент 'Новый проект' так и не появился после " + maxAttempts + " попыток");
        }
    }

    public void openAgileBoardsPage() {
        int maxAttempts = 5;
        int attempts = 0;
        boolean success = false;

        while (attempts < maxAttempts) {
            try {
                WaitUtils.waitForElementClickable(driver, agileBoards).click();
                WaitUtils.waitForElementVisible(driver, agilePageHeader);
                success = true;
                break;
            } catch (Exception e) {
                logger.info("Попытка " + (attempts + 1) + " не удалась: " + e.getMessage());
                attempts++;
            }
        }

        if (!success) {
            throw new RuntimeException("Элемент 'Новый проект' так и не появился после " + maxAttempts + " попыток");
        }
    }

    public void openProfile() {
        driver.findElement(profile).click();
    }
}