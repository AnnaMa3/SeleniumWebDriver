package base;

import PageFactory.HomePageFactory;

import static driver.Driver.getDriver;
import static driver.Driver.tearDown;

public class BaseTest {

    protected static HomePageFactory homePageFactory;

    @org.testng.annotations.BeforeMethod
    public void init() {
        getDriver().get("https://passport.yandex.com/auth?retpath=https%3A%2F%2Fpassport.yandex.com%2F&noreturn=1");
        homePageFactory = new HomePageFactory();
    }

    @org.testng.annotations.AfterMethod
    public void closeBrowser() {
        tearDown();
    }

}
