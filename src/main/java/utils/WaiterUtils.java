package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaiterUtils {
    private WebDriver driver;

    public WaiterUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementExist(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(500)).until(webDriver -> {
            try {
                return element.isEnabled();
            } catch (NoSuchElementException ex) {
                return false;
            }
        });
    }

    public void waitForElementDisplayed(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(500)).until(webDriver -> {
            try {
                return element.isDisplayed();
            } catch (NoSuchElementException ex) {
                return false;
            }
        });
    }
}
