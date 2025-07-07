package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.IssuePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IssuesTest extends BaseTest {
    private IssuePage issuePage;

    @ParameterizedTest
    @MethodSource("readTaskNames")
    @DisplayName("1. Тест создания новой задачи")
    public void issueCreateTest(String taskName) {
        issuePage = new IssuePage(driver);
        menuPage.openProjectsPage();
        issuePage.createNewIssue(taskName);
        String actualTitle = issuePage.getIssueTitle();
        assertEquals(taskName, actualTitle, "Провал. Не удалось создать задачу.");
    }
}