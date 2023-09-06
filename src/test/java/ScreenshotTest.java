import PageFactory.TestListener;
import com.google.common.collect.ImmutableMap;
import config.TestProperties;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;



@Listeners(TestListener.class)
public class ScreenshotTest extends BaseTest{
    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");

    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(ScreenshotTest.class);


    @Epic("Report")
    @Parameters({"browser", "browser_version"})

    @Test(description = "Screenshot in Step")
    @Description("Report")
    @TmsLink("Test-1000")
    @Severity(SeverityLevel.CRITICAL)
    public void screenshotTest(@Optional("Chrome") String browser, @Optional("116.0.5845.141") String browser_version){
        login(LOGIN, PASSWORD);
        Assertions.fail("Test is failed");

        logout();
    }
}
