import PageFactory.AccountPageFactory;
import PageFactory.HomePageFactory;
import PageFactory.TestListener;
import config.TestProperties;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestListener.class)
public class ScreenshotTest extends BaseTest{
    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");

    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(ScreenshotTest.class);

    @Test
    public void screenshotTest(){
        login(LOGIN, PASSWORD);
        
        Assertions.fail("Test is failed");

        logout();
        Assertions.assertTrue(HomePageFactory.isDisplayed());
    }
}
