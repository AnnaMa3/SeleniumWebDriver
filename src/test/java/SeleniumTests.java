import base.BaseTest;
import config.TestProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.AccountPage;

public class SeleniumTests extends BaseTest {
    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");


    @Test
    public void loginTest(){

        homePage.inputLogin(LOGIN);
        homePage.clickLoginButton();
        homePage.inputPassword(PASSWORD);
        homePage.clickLoginButton();

        Assertions.assertTrue(AccountPage.isDisplayed());

    }


}
