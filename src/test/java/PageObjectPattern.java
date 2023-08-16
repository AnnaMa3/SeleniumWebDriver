import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageObjectPattern extends BaseTest {


    @Test
    public void loginTest(){
        homePagePOM.enterLogin();
        homePagePOM.clickLoginButton();
        homePagePOM.enterPassword();
        homePagePOM.clickLoginButton();

        Assert.assertTrue(homePagePOM.isDisplayed());
    }

    @Test
    public void logoutTest(){
        homePagePOM.enterLogin();
        homePagePOM.clickLoginButton();
        homePagePOM.enterPassword();
        homePagePOM.clickLoginButton();

        homePagePOM.clickUserAvatar();
        homePagePOM.clickSignOutButton();


        Assert.assertTrue(homePagePOM.isHomePageDisplayed());

    }


}
