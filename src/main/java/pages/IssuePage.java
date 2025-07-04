package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import utils.WaitUtils;

public class IssuePage {
    private WebDriver driver;

    private By myProject = By.xpath("//a[@data-test='ring-link' and text()='Тестовый проект 1']");
    private By createNewIssueButton = By.xpath("//a[@data-test='ring-link' and text()='Новая задача']");
    private By issueNameField = By.xpath("//textarea[@data-test='summary']");
    private By submitNewIssueButton = By.xpath("//button[@data-test='submit-button']");
    private By issueTitle = By.xpath("//h1[@data-test='ticket-summary']");

    /*
    private By showMoreBtn = By.xpath("//button[@data-test='ring-link fields-expander']");
    private By issueTypeDropdown = By.xpath("//span[@data-test='ring-tooltip field-value' and text()='Ошибка']");
    private By issueType = By.xpath("//span[@data-test='ring-list-item-label' and text()='Задание']");
    private By issueStatusDropdown = By.xpath("//span[@data-test='ring-tooltip field-value' and text()='Зарегистрирована']");
    private By issueStatus = By.xpath("//button[.//span[text()='Открыта']]");
     */

    public IssuePage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewIssue(String issueName) {
        driver.findElement(myProject).click();
        driver.findElement(createNewIssueButton).click();
        driver.findElement(issueNameField).sendKeys(issueName);

        /*
        Actions actions = new Actions(driver);
        driver.findElement(showMoreBtn).click();
        WaitUtils.waitForElementClickable(driver, issueTypeDropdown).click();
        WaitUtils.waitForElementClickable(driver, issueType).click();
        WaitUtils.waitForElementClickable(driver, showMoreBtn).click();
        WaitUtils.waitForElementClickable(driver, issueStatusDropdown).click();
        WaitUtils.waitForElementClickable(driver, issueStatus).click();
        actions.moveToElement(driver.findElement(issueStatus)).click().perform();
         */

        WaitUtils.waitForElementClickable(driver, submitNewIssueButton).click();
    }

    public String getIssueTitle() {
        return driver.findElement(issueTitle).getText();
    }
}