package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class BrowserUtils {
    private static final Logger logger = getLogger(BrowserUtils.class);

    public static void refreshIfMainElementMissing(WebDriver driver, By locator) {
        try {
            List<WebElement> elements = driver.findElements(locator);

            if (elements.isEmpty() || !elements.get(0).isDisplayed()) {
                logger.info("Элемент не найден или не виден. Обновляем страницу...");
                driver.navigate().refresh();
            }

        } catch (Exception e) {
            logger.info("Ошибка при проверке элемента. Обновляем страницу...");
            driver.navigate().refresh();
        }
    }
}