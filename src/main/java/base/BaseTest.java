package base;

import config.TestProperties;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import page.HomePage;

import static driver.Driver.getDriver;
import static driver.Driver.tearDown;

public class BaseTest {

    protected static HomePage homePage;

    @org.testng.annotations.BeforeMethod
    public void init() {
        getDriver().get("https://passport.yandex.com/auth?retpath=https%3A%2F%2Fpassport.yandex.com%2F&noreturn=1");
        homePage = new HomePage();
    }

    @org.testng.annotations.AfterMethod
    public void closeBrowser() {
        tearDown();
    }

}
