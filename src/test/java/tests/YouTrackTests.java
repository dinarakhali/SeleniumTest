package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YouTrackTests extends BaseTest {
    private ProjectsPage projectsPage;
    private IssuePage issuePage;
    private AgileBoardsPage agileBoardsPage;

    @BeforeAll
    public static void setDriver() {
        //WebDriverManager.edgedriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("1. Тест входа в аккаунт")
    public void loginTest() {
        String actualTitle = loginPage.getProfileTitle();
        String expectedTitle = "admin";
        assertEquals(expectedTitle, actualTitle, "Провал");
    }

    @ParameterizedTest
    @MethodSource("readProjectNames")
    @DisplayName("2. Тест создания нового проекта")
    public void projectCreateTest(String projectName) {
        projectsPage = new ProjectsPage(driver, wait);
        menuPage.openProjectsPage();

        projectsPage.createNewProject(projectName);
        String actualTitle = projectsPage.getProjectTitle();
        assertEquals(projectName, actualTitle, "Провал");
    }

    @ParameterizedTest
    @MethodSource("readTaskNames")
    @DisplayName("3. Тест создания новой задачи")
    public void issueCreateTest(String taskName) {
        issuePage = new IssuePage(driver);
        menuPage.openProjectsPage();

        issuePage.createNewIssue(taskName);
        String actualTitle = issuePage.getIssueTitle();
        assertEquals(taskName, actualTitle, "Провал");
    }

    @ParameterizedTest
    @Disabled
    @MethodSource("readProjectNames")
    @DisplayName("4. Тест создания новой доски Agile")
    public void agileBoardCreateTest(String projectName) {
        agileBoardsPage = new AgileBoardsPage(driver);
        menuPage.openAgileBoardsPage();
        agileBoardsPage.createNewAgileBoard("Project Board", projectName);
        String actualTitle = agileBoardsPage.getBoardTitle();
        assertEquals(projectName, actualTitle, "Провал");
    }

    @Test
    @Disabled
    @DisplayName("5. Тест добавления задачи на доску")
    public void issueAddToBoardTest() {
        agileBoardsPage = new AgileBoardsPage(driver);
        menuPage.openAgileBoardsPage();
        agileBoardsPage.addIssueToBoard();
        //agileBoardsPage.issueDragNDrop();
        String actualTitle = agileBoardsPage.issueStatusCheck();
        String expectedTitle = "Тестовая задача 1";
        assertEquals(expectedTitle, actualTitle, "Провал");
    }

    @Test
    @Disabled
    @DisplayName("6. Тест удаления доски")
    public void boardDeleteTest() {
        agileBoardsPage = new AgileBoardsPage(driver);
        menuPage.openAgileBoardsPage();
        agileBoardsPage.boardDelete();
        boolean hasErrorMessage = agileBoardsPage.hasErrorMessage();
        assertTrue(hasErrorMessage, "Провал");
    }

    @Test
    @Disabled
    @DisplayName("7. Тест удаления проекта")
    public void testDeleteTest() {
        projectsPage = new ProjectsPage(driver, wait);
        menuPage.openProjectsPage();
        projectsPage.deleteMyProject();
    }

    @Test
    @DisplayName("8. Проверка снятия скриншота")
    public void screenTest() {
        String actualTitle = loginPage.getProfileTitle();
        String expectedTitle = "12345";
        assertEquals(expectedTitle, actualTitle, "Провал");
    }
}