package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {
    @Override
    protected boolean shouldLogin() {
        return false;
    }

    @ParameterizedTest
    @CsvSource({
            "admin, sa85BqdhWn3UeHp",
            "user1, 12345",
            "Tester, 315DSCcc2-_"
    })
    @DisplayName("1. Тест входа и выхода из аккаунта")
    public void loginTest(String login, String password) {
        loginPage = new LoginPage(driver, wait);
        loginPage.login(login, password);
        String actualTitle = loginPage.getProfileTitle();
        assertEquals(login, actualTitle, "Провал. Не удалось выполнить вход в аккаунт.");
        loginPage.logout();
        assertTrue(loginPage.isSubmitBtnVisible(), "Провал");
    }
}