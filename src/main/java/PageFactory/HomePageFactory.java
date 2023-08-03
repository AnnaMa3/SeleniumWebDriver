package PageFactory;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageFactory extends BasePage {

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

    public HomePageFactory(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void setLogin(String login){
        loginField.sendKeys(login);
    }

    public PasswordPageFactory clickLoginButton(){
        loginButton.click();
        return new PasswordPageFactory(driver);
    }

    public static boolean isDisplayed() {
        return authContainer.isDisplayed();
    }

}
