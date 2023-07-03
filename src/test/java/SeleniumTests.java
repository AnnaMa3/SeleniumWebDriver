import base.BaseTest;
import config.TestProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.AccountPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SeleniumTests extends BaseTest {
    private static final String LOGIN1 = TestProperties.get("login1");
    private static final String PASSWORD1 = TestProperties.get("password1");
    private static final String LOGIN2 = TestProperties.get("login2");
    private static final String PASSWORD2 = TestProperties.get("password2");

    @FindBy(css = ".CurrentAccount-displayName")
    private static WebElement name;


    @DataProvider (name = "credentials")
    public Object[][] getCredentials() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
        properties.load(fileInputStream);

        return new Object[][]{
                {LOGIN1, PASSWORD1},
                {LOGIN2, PASSWORD2}
        };
    }

    @Test (dataProvider = "credentials")
    public void loginTest(String login, String password) throws InterruptedException {

        homePage.inputLogin(login);
        homePage.clickLoginButton();

        homePage.nameIsDisplayed();


        homePage.inputPassword(password);
        homePage.clickLoginButton();

        Assert.assertTrue(AccountPage.isDisplayed());

        //static wait or hard-code delay
        Thread.sleep(5000);

    }


}
