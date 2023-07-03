package page;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    @FindBy(css = "#passp-field-login")
    private WebElement loginField;

    @FindBy(css = ".Button2_type_submit")
    private WebElement loginButton;

    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordField;

    @FindBy(css = ".CurrentAccount-displayName")
    private WebElement name;

    public HomePage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void inputLogin(String login) {
        loginField.sendKeys(login);
    }

    public void inputPassword(String password) {
        passwordField.sendKeys(password);
    }

    public AccountPage clickLoginButton(){
        loginButton.click();
        return new AccountPage();
    }

    public void nameIsDisplayed() {
        waiter.waitForElementDisplayed(name);
    }


}
