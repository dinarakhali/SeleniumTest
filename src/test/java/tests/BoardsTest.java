package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.AgileBoardsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardsTest extends BaseTest {
    private AgileBoardsPage agileBoardsPage;

    @Test
    @DisplayName("1. Тест создания новой доски Agile")
    public void agileBoardCreateTest() {
        agileBoardsPage = new AgileBoardsPage(driver);
        menuPage.openAgileBoardsPage();
        agileBoardsPage.createNewAgileBoard("Project Board", "Тестовый проект 1");
        String expectedTitle = "Project Board";
        String actualTitle = agileBoardsPage.getBoardTitle();
        assertEquals(expectedTitle, actualTitle, "Провал. Не удалось создать доску Agile.");
        agileBoardsPage.boardDelete();
        boolean hasErrorMessage = agileBoardsPage.hasErrorMessage();
        assertTrue(hasErrorMessage, "Провал. Не удалось удалить доску.");
    }

    @Test
    @DisplayName("2. Тест добавления задачи на доску")
    public void issueAddToBoardTest() {
        agileBoardsPage = new AgileBoardsPage(driver);
        menuPage.openAgileBoardsPage();
        agileBoardsPage.createNewAgileBoard("Board for Issue", "Тестовый проект 1");
        agileBoardsPage.addIssueToBoard();
        String actualTitle = agileBoardsPage.issueStatusCheck();
        String expectedTitle = "Тестовая задача 1";
        assertEquals(expectedTitle, actualTitle, "Провал. Не удалось добавить задачу на доску.");
        agileBoardsPage.boardDelete();
        boolean hasErrorMessage = agileBoardsPage.hasErrorMessage();
        assertTrue(hasErrorMessage, "Провал. Не удалось удалить доску.");
    }
}