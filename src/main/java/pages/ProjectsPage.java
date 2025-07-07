package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WaitUtils;

public class ProjectsPage {
    private WebDriver driver;

    protected static By newProjectButton = By.xpath("//span[normalize-space(text())='Новый проект']");
    private By defoultTemplateButton = By.xpath("//button[@data-test='template'][1]");
    private By useTemplateButton = By.xpath("//button[@data-test='accept-button']");
    private By projectNameField = By.xpath("//input[@data-test='project-name']");
    private By createProjectButton = By.xpath("//button[@data-test='create-button']");
    private By skipSettingsButton = By.xpath("//button[@data-test='skip']");
    private By projectTitle = By.xpath("//h1[@data-test='project-heading']");
    private By delete = By.xpath("//button[text()='Удалить']");
    private By projectNameForDeleteField = By.xpath("(//div[@data-test='ring-input'])[2]//input");
    private By deleteProjectButton = By.xpath("//button[.//span[text()='Удалить проект']]");

    public ProjectsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
    }

    public void createNewProject(String projectName) {
        WaitUtils.waitForElementClickable(driver, newProjectButton).click();
        driver.findElement(defoultTemplateButton).click();
        driver.findElement(useTemplateButton).click();
        driver.findElement(projectNameField).sendKeys(projectName);
        driver.findElement(createProjectButton).click();
        driver.findElement(skipSettingsButton).click();
    }

    public String getProjectTitle() {
        return driver.findElement(projectTitle).getText();
    }

    public void deleteMyProject(String projectName) {
        String locator = String.format("//div[@data-test-name='%s']//following::div[@data-test='ring-dropdown project-app']", projectName);
        By more = By.xpath(locator);
        driver.findElement(more).click();
        driver.findElement(delete).click();
        WaitUtils.waitForElementVisible(driver, projectNameForDeleteField).sendKeys(projectName);
        driver.findElement(deleteProjectButton).click();
    }

    public boolean isProjectTitleVisible() {
        return driver.findElement(projectTitle).isDisplayed();
    }
}