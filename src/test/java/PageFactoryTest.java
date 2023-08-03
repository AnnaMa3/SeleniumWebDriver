import PageFactory.AccountPageFactory;
import PageFactory.HomePageFactory;
import config.TestProperties;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;


public class PageFactoryTest extends BaseTest {

    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");


    @Test
    public void loginAndLogoutTest(){
        login(LOGIN, PASSWORD);

        AccountPageFactory accountPageFactory = new AccountPageFactory(driver);
        Assertions.assertTrue(AccountPageFactory.isDisplayed());
        Assertions.assertEquals(AccountPageFactory.getUserName(), LOGIN);

        logout();
        Assertions.assertTrue(HomePageFactory.isDisplayed());
    }


}
