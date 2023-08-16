package PageObjectPattern;

import base.BasePage;
import config.TestProperties;
import org.openqa.selenium.By;

public class HomePagePOM extends BasePage {

    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");

    public HomePagePOM(){
        super();
        this.driver = driver;
    }

    public void enterLogin(){
        driver.findElement(By.cssSelector("#passp-field-login")).sendKeys(LOGIN);
    }

    public void clickLoginButton(){
        driver.findElement(By.cssSelector(".Button2_type_submit")).click();
    }

    public void enterPassword(){
        driver.findElement(By.cssSelector("#passp-field-passwd")).sendKeys(PASSWORD);
    }

    public boolean isDisplayed() {
        return driver.findElement(By.cssSelector(".PageTemplate_root__d4N4i")).isDisplayed();
    }

    public void clickUserAvatar(){
        driver.findElement(By.cssSelector(".UserID-Avatar")).click();
    }

    public void clickSignOutButton(){
        driver.switchTo().frame(driver.findElement(By.cssSelector(".UserWidget-Iframe")));
        driver.findElement(By.xpath("//*[contains(@data-testid, 'logout')]")).click();
    }

    public boolean isHomePageDisplayed() {
        return driver.findElement(By.cssSelector(".passp-auth-content")).isDisplayed();
    }

}
