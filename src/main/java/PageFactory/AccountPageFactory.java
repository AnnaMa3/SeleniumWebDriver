package PageFactory;

import base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPageFactory extends BasePage {

    @FindBy(css = ".PageTemplate_root__d4N4i")
    private static WebElement personalContainer;
    @FindBy(css = ".UserID-Avatar")
    private WebElement userAvatar;

    @FindBy(css = ".UserID-Avatar")
    private static WebElement userAvatarIs;

    @FindBy(css = ".UserWidget-Iframe")
    private static WebElement frame;

    @FindBy(xpath = "//*[contains(@data-testid, 'logout')]")
    private WebElement signOutButton;

    @FindBy(css = ".Subname")
    private static WebElement userName;

    public AccountPageFactory(WebDriver driver) {
        super(driver);
        initElements();
    }

    public HomePageFactory logout(){
//        userAvatar.click();
//        driver.switchTo().frame(frame);
        signOutButton.click();
        return new HomePageFactory(driver);
    }

    public static boolean isDisplayed() {
        return personalContainer.isDisplayed();
    }

    public static String getUserName() {
        userAvatarIs.click();
        driver.switchTo().frame(frame);
        return userName.getText();
    }


}
