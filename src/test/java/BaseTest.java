import PageFactory.AccountPageFactory;
import PageFactory.HomePageFactory;
import PageFactory.PasswordPageFactory;
import config.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static driver.Driver.getDriver;
import static driver.Driver.tearDown;

public class BaseTest {
    protected WebDriver driver;
    private static final String URL = TestProperties.get("url");


    @org.testng.annotations.BeforeMethod
    public void init() {
        getDriver().get(URL);
    }

    public AccountPageFactory login(String login, String password){
        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.setLogin(login);
        homePageFactory.clickLoginButton();
        PasswordPageFactory passwordPageFactory = new PasswordPageFactory(driver);
        passwordPageFactory.setPassword(password);
        passwordPageFactory.clickLogin();
        return new AccountPageFactory(driver);

    }

    public HomePageFactory logout(){
        AccountPageFactory accountPageFactory = new AccountPageFactory(driver);
        accountPageFactory.logout();
        return new HomePageFactory(driver);
    }

    @org.testng.annotations.AfterMethod
    public void closeBrowser() {
        tearDown();
    }

}
