package base;

import PageObjectPattern.HomePagePOM;
import config.TestProperties;


import static driver.Driver.getDriver;
import static driver.Driver.tearDown;

public class BaseTest {

    protected static HomePagePOM homePagePOM;
    private static final String URL = TestProperties.get("url");

    @org.testng.annotations.BeforeMethod
    public void init() {
        getDriver().get(URL);
        homePagePOM = new HomePagePOM();
    }

    @org.testng.annotations.AfterMethod
    public void closeBrowser() {
        tearDown();
    }

}
