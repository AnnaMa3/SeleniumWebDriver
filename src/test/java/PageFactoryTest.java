import PageFactory.AccountPageFactory;
import PageFactory.HomePageFactory;
import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;


public class PageFactoryTest extends BaseTest {


    @Test
    public void loginAndLogoutTest(){
        homePageFactory.login();

        AccountPageFactory accountPageFactory = new AccountPageFactory();
        Assertions.assertTrue(AccountPageFactory.isDisplayed());
        accountPageFactory.logout();

        Assertions.assertTrue(HomePageFactory.isDisplayed());
    }


}
