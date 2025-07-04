package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowserUtils {

    public static void refreshIfMainElementMissing(WebDriver driver, By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);

            if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                System.out.println("Элемент не найден или не виден. Обновляем страницу...");
                driver.navigate().refresh();
            }

        } catch (Exception e) {
            System.out.println("Ошибка при проверке элемента. Обновляем страницу...");
            driver.navigate().refresh();
        }
    }
}