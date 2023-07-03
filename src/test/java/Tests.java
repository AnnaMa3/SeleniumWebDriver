import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WaiterUtils;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import static driver.Driver.tearDown;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;

public class Tests {
    protected WebDriver driver;
    protected WaiterUtils waiter;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[text()[contains(.,'Last Name :')]]")
    private static WebElement user;

    @BeforeMethod
    public void addSettings(){
        driver = new ChromeDriver();
        waiter = new WaiterUtils(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void multiSelectTest(){

        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");

        WebElement multiSelect = driver.findElement(By.id("multi-select"));
        Select select = new Select(multiSelect);

        List<WebElement> options = select.getOptions();
        Random random = new Random();

        int numberOfOptions = options.size();

        int[] selectedOptionsIndex = random.ints(0, numberOfOptions).distinct().limit(3).toArray();

        for (int index:selectedOptionsIndex){
            select.selectByIndex(index);
            WebElement selectOption = options.get(index);

            String optionStyle = selectOption.getDomProperty("selected");
            Assert.assertTrue(optionStyle.equals("true"));

        }
    }

    @Test
    public void alertsTextTest() {
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.cssSelector("[onclick=\"myConfirmFunction()\"]")).click();
        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "Press a button!");
    }

    @Test
    public void alertsButtonsTest(){
        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.cssSelector("[onclick=\"myConfirmFunction()\"]")).click();
        Assert.assertEquals(alertIsPresent().toString(), "alert to be present");
    }

    @Test
    public void alertsJavaScriptTest() {
        String enteredText = "Test Text";

        driver.get("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");

        driver.findElement(By.cssSelector("[onclick=\"myPromptFunction()\"]")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(enteredText);
        alert.accept();

        String actualText = driver.findElement(By.cssSelector("#prompt-demo")).getText();
        Assert.assertEquals("You have entered '" + enteredText + "' !", actualText);
    }

    @Test
    public void waitForUserTest(){
        driver.get("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");

        driver.findElement(By.cssSelector(".btn-default")).click();
        WebElement loading = driver.findElement(By.id("loading"));

        wait.until(ExpectedConditions.textToBePresentInElement(loading, "Last Name"));

        Assert.assertEquals(ExpectedConditions.textToBePresentInElement(loading, "Last Name").toString(), "text ('Last Name') to be present in element " + loading);
    }

    @Test
    public void percentageTest(){
        driver.get("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");

        driver.findElement(By.cssSelector("#cricle-btn")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector(".percenttext"), "50%"));
        driver.navigate().refresh();

        Assert.assertEquals(driver.findElement(By.cssSelector(".percenttext")).getText(), "0%");
    }


    @Test
    public void customObjectTest() throws IOException {

        driver.get("https://demo.seleniumeasy.com/table-sort-search-demo.html");

        WebElement numberOfRows = driver.findElement(By.cssSelector("select[aria-controls=\"example\"]"));
        Select numberOfRowsSelect = new Select(numberOfRows);
        numberOfRowsSelect.selectByIndex(0);

        List<CustomObject> customObjects = getCustomObject(60, 320800);

        for (CustomObject customObject : customObjects){
            System.out.println(customObject.toString());
        }

    }

    private List<CustomObject> getCustomObject(int ageLimit, double salaryLimit){

        List<CustomObject> customObjects = new ArrayList<>();

        List<WebElement> pages = driver.findElements(By.cssSelector(".paginate_button"));
        int numberOfPages = pages.size();

        for (int j = 1; j < numberOfPages-1; j++) {
            List<WebElement> tableRows = driver.findElements(By.cssSelector("tbody tr"));

            for (int i = 0; i < tableRows.size(); i++) {
                WebElement row = tableRows.get(i);

                    String name = row.findElement(By.xpath("td[1]")).getText();
                    String position = row.findElement(By.xpath("td[2]")).getText();
                    String office = row.findElement(By.xpath("td[3]")).getText();

                    int age = Integer.parseInt(row.findElement(By.xpath("td[4]")).getText());
                    String fullSalary = row.findElement(By.xpath("td[6]")).getText();

                    String numSalary = fullSalary.replaceAll("[^0-9]", "");
                    double salary = Double.parseDouble(numSalary);

                    if (age > ageLimit && salary <= salaryLimit) {
                        CustomObject customObject = new CustomObject(name, position, office);
                        customObjects.add(customObject);
                    }
                }


                driver.findElement(By.cssSelector(".next")).click();
        }
        return customObjects;
    }

    @AfterMethod
    public void closeBrowser() {
        tearDown();
    }
}
