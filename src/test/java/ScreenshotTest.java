import PageFactory.AccountPageFactory;
import PageFactory.HomePageFactory;
import config.TestProperties;
import driver.Driver;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ScreenshotTest extends BaseTest{
    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");

    private WebDriver driver;

    @Test
    public void screenshotTest(){
        login(LOGIN, PASSWORD);

        AccountPageFactory accountPageFactory = new AccountPageFactory(driver);
        Assertions.assertTrue(AccountPageFactory.isDisplayed());

        driver = Driver.getDriver();
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileHandler.copy(screenshotFile, new File("C:/Users/AnnaMatveenko/OneDrive - Coherent Solutions/SPARTA/Automation/homepage.png"));
            System.out.println("Screenshot of Homepage is saved.");
        } catch (IOException e){
            e.printStackTrace();
        }

        Assertions.assertEquals(AccountPageFactory.getUserName(), LOGIN);

        logout();
        Assertions.assertTrue(HomePageFactory.isDisplayed());
    }
}
