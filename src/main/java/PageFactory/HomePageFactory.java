package PageFactory;

import base.BasePage;
import config.TestProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageFactory extends BasePage {

    private static final String LOGIN = TestProperties.get("login");
    private static final String PASSWORD = TestProperties.get("password");
    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = ".Button2_type_submit")
    private WebElement loginButton;

    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordField;

    @FindBy(css = ".CurrentAccount-displayName")
    private WebElement name;

    @FindBy(css = ".passp-auth-content")
    private static WebElement authContainer;

    public HomePageFactory() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void login(){
        loginField.sendKeys(LOGIN);
        loginButton.click();
        passwordField.sendKeys(PASSWORD);
        loginButton.click();
    }

    public static boolean isDisplayed() {
        return authContainer.isDisplayed();
    }

}
