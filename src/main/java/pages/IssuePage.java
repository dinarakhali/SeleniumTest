package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class IssuePage {
    private WebDriver driver;

    private By myProject = By.xpath("//a[@data-test='ring-link' and text()='Тестовый проект 1']");
    private By createNewIssueButton = By.xpath("//a[@data-test='ring-link' and text()='Новая задача']");
    private By issueNameField = By.xpath("//textarea[@data-test='summary']");
    private By submitNewIssueButton = By.xpath("//button[@data-test='submit-button']");
    private By issueTitle = By.xpath("//h1[@data-test='ticket-summary']");

    public IssuePage(WebDriver driver) {
        this.driver = driver;
    }

    public void createNewIssue(String issueName) {
        driver.findElement(myProject).click();
        driver.findElement(createNewIssueButton).click();
        driver.findElement(issueNameField).sendKeys(issueName);

        WaitUtils.waitForElementClickable(driver, submitNewIssueButton).click();
    }

    public String getIssueTitle() {
        return driver.findElement(issueTitle).getText();
    }
}