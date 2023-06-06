package base;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import utils.WaiterUtils;

public abstract class BasePage {
    protected WebDriver driver;
    protected WaiterUtils waiter;

    public BasePage() {
        this.driver = Driver.getDriver();
        this.waiter = new WaiterUtils(driver);
    }
}
