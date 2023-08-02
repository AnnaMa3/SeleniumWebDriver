package PageFactory;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPageFactory extends BasePage {

    @FindBy(css = ".PageTemplate_root__d4N4i")
    private static WebElement personalContainer;
    @FindBy(css = ".UserID-Avatar")
    private WebElement userAvatar;

    @FindBy(css = ".UserWidget-Iframe")
    private WebElement frame;

    @FindBy(xpath = "//*[contains(@data-testid, 'logout')]")
    private WebElement signOutButton;
    public AccountPageFactory() {
        super();
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        userAvatar.click();
        driver.switchTo().frame(frame);
        signOutButton.click();
    }
    public static boolean isDisplayed() {
        return personalContainer.isDisplayed();
    }
}
