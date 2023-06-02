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
    private static final String BASE_URL = TestProperties.get("url");

    protected static HomePage homePage;

    @BeforeEach
    public void init() {
        getDriver().get(BASE_URL);
        homePage = new HomePage();
    }

    @AfterEach
    public void closeBrowser() {
        tearDown();
    }

}
