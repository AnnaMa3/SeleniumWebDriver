import PageFactory.AccountPageFactory;
import PageFactory.HomePageFactory;
import PageFactory.TestListener;
import config.TestProperties;
import driver.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;


@Listeners(TestListener.class)
public class ScreenshotTest extends BaseTest{
    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");

    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(ScreenshotTest.class);

    @Test
    public void screenshotTest(){
        login(LOGIN, PASSWORD);

        AccountPageFactory accountPageFactory = new AccountPageFactory(driver);
        Assertions.assertTrue(AccountPageFactory.isDisplayed());
        AccountPageFactory.createArtifactsFolder();

        driver = Driver.getDriver();
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

        try{
            AccountPageFactory.createArtifactsFolder();
            File screenshotFile = new File("target/artifacts/homepage.png");
            ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);
            logger.info("Full-page screenshot is saved.");
        } catch (IOException e){
            logger.error("Failed to save");
        }


        Assertions.assertEquals(AccountPageFactory.getUserName(), LOGIN);

        logout();
        Assertions.assertTrue(HomePageFactory.isDisplayed());
    }
}
