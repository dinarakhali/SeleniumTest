package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ProjectsPage;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectsTest extends BaseTest {
    private ProjectsPage projectsPage;

    @ParameterizedTest
    @MethodSource("readProjectNames")
    @DisplayName("1. Тест создания и удаления проекта")
    public void projectCreateTest(String projectName) {
        projectsPage = new ProjectsPage(driver, wait);
        menuPage.openProjectsPage();
        projectsPage.createNewProject(projectName);
        String actualTitle = projectsPage.getProjectTitle();
        assertEquals(projectName, actualTitle, "Провал. Не удалось создать проект.");
        menuPage.openProjectsPage();
        projectsPage.deleteMyProject(projectName);
    }
}