package utils;

import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotOnFailureExtension implements AfterTestExecutionCallback, BeforeEachCallback {
    private WebDriver driver;

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        boolean testFailed = context.getExecutionException().isPresent();

        if (testFailed && driver != null) {
            takeScreenshot(context);
        }

        if (driver != null) {
            driver.quit();
            System.out.println("Драйвер закрыт");
        }
    }

    private void takeScreenshot(ExtensionContext context) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage img = ImageIO.read(srcFile);

            String testName = context.getDisplayName().replaceAll("[^а-яА-Яa-zA-Z0-9\\.\\-]", "_");
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

            File dir = new File("screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File destFile = new File(dir, testName + "_" + timestamp + ".png");
            ImageIO.write(img, "png", destFile);
            System.out.println("Скриншот сохранён: " + destFile.getPath());
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении скриншота: " + e.getMessage());
        }
    }
}