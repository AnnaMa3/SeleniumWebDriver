package base;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.WaiterUtils;

public abstract class BasePage {
    protected WebDriver driver;
    protected WaiterUtils waiter;

    public BasePage(WebDriver driver) {
        this.driver = Driver.getDriver();
        this.waiter = new WaiterUtils(driver);
    }

    public void initElements(){
        PageFactory.initElements(driver, this);
    }
}
