import PageFactory.HomePageFactory;
import PageFactory.PasswordPageFactory;
import org.openqa.selenium.WebDriver;

import static driver.Driver.getDriver;
import static driver.Driver.tearDown;

public class BaseTest {
    protected WebDriver driver;


    @org.testng.annotations.BeforeMethod
    public void init() {
        getDriver().get("https://passport.yandex.com/auth?retpath=https%3A%2F%2Fpassport.yandex.com%2F&noreturn=1");
    }

    public void login(String login, String password){
        HomePageFactory homePageFactory = new HomePageFactory(driver);
        homePageFactory.setLogin(login);
        homePageFactory.clickLoginButton();
        PasswordPageFactory passwordPageFactory = new PasswordPageFactory(driver);
        passwordPageFactory.setPassword(password);
        passwordPageFactory.clickLogin();

    }

    @org.testng.annotations.AfterMethod
    public void closeBrowser() {
        tearDown();
    }

}
