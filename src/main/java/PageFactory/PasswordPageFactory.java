package PageFactory;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordPageFactory extends BasePage {
    @FindBy(css = "#passp-field-passwd")
    private WebElement passwordField;

    @FindBy(css = ".Button2_type_submit")
    private WebElement loginButton;


    public  PasswordPageFactory(WebDriver driver) {
        super(driver);
        initElements();
    }

    public void setPassword(String password){
        passwordField.sendKeys(password);
    }

    public AccountPageFactory clickLogin(){
        loginButton.click();
        return new AccountPageFactory(driver);
    }

}
