package page;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage extends BasePage {

    @FindBy(css = ".PageTemplate_root__d4N4i")
    private static WebElement personalContainer;

    public AccountPage() {
        super();
        PageFactory.initElements(driver, this);
    }

    public static boolean isDisplayed() {
        return personalContainer.isDisplayed();
    }

}