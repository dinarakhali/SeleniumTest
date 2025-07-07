package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.List;

public class AgileBoardsPage {
    private WebDriver driver;
    protected static By agilePageHeader = By.xpath("//yt-top-bar[@data-test='header']");

    //если есть уже созданные доски, то:
    private By createNewSmthDropdown = By.xpath("//button[@data-test='createActionsHeaderDropdown']");
    private By createNewBoardDropdown = By.xpath("//span[@data-test='ring-list-item-label' and text()='Доску...']");

    //общее на оба варианта
    private By createScrumBoardButton = By.xpath("//button[@data-test='createScrumBoard']");
    private By selectProjectDropdown = By.xpath("//input[@data-test='ring-select__focus']");
    private By boardNameField = By.xpath("//input[@name='name']");
    private By myProjectName = By.xpath("//span[@title='Тестовый проект 1']");
    private By saveNewBoardButton = By.xpath("//button[@data-test='saveNewBoard']");
    private By boardTitle = By.xpath("//span[text()='Project Board']");

    //добавление задач на доску
    private By backlogToggler = By.xpath("//button[@data-test='backlogToggler']");
    private By addIssueButton = By.xpath("//yt-icon[@data-test='addIssueToSprintAction']");
    private By myIssueStatusOnBoard = By.xpath("//span[contains(text(), 'Task') or contains(text(), 'Тестовая задача')]");

    //удаление доски
    private By moreIcon = By.xpath("//div[@data-test='ring-dropdown']");
    private By deleteButton = By.xpath("//span[@data-test='ring-list-item-label' and text()='Удалить']");
    private By deleteBoardButton = By.xpath("//button[@data-test='confirm-ok-button']");
    private By errorMessage = By.xpath("//div[@data-test='error-message']");

    public AgileBoardsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewAgileBoard(String newBoardName, String projectName) {
        List<WebElement> elements = driver.findElements(createNewSmthDropdown);

        if (!elements.isEmpty() && elements.get(0).isEnabled()) {
            elements.get(0).click();
            WaitUtils.waitForElementClickable(driver, createNewBoardDropdown).click();
        }

        driver.findElement(createScrumBoardButton).click();
        driver.findElement(boardNameField).sendKeys(newBoardName);

        driver.findElement(selectProjectDropdown).click();
        WaitUtils.waitForElementClickable(driver, myProjectName).click();
        driver.findElement(saveNewBoardButton).click();
    }

    public void addIssueToBoard() {
        WaitUtils.waitForElementClickable(driver, backlogToggler).click();
        WaitUtils.waitForPageLoadComplete(driver);
        WaitUtils.waitForElementClickable(driver, addIssueButton).click();
        WaitUtils.waitForElementClickable(driver, backlogToggler).click();
    }

    public String issueStatusCheck() {
        return driver.findElement(myIssueStatusOnBoard).getText();
    }

    public void boardDelete() {
        WaitUtils.waitForElementClickable(driver, moreIcon).click();
        WaitUtils.waitForElementClickable(driver, deleteButton).click();
        WaitUtils.waitForElementClickable(driver, deleteBoardButton).click();
        driver.navigate().refresh();
    }

    public String getBoardTitle() {
        return driver.findElement(boardTitle).getText();
    }

    public boolean hasErrorMessage() {
        return driver.findElement(errorMessage).isDisplayed();
    }
}
