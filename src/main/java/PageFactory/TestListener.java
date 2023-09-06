package PageFactory;

import driver.Driver;
import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class TestListener implements ITestListener {
    private WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }


    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = iTestResult.getMethod().getMethodName();
        driver = Driver.getDriver();

        Object output = ((JavascriptExecutor) driver).executeScript("return window.devicePixelRatio");
        String value = String.valueOf(output);
        float windowDPR = Float.parseFloat(value);

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies
                .viewportPasting(ShootingStrategies.scaling(windowDPR),100)).takeScreenshot(driver);

        byte[] screenshotByte = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);;
        AccountPageFactory.createArtifactsFolder();

        try {
            File screenshotFile = new File("target/artifacts/"+testName+"failure.png");
            ImageIO.write(screenshot.getImage(), "PNG", screenshotFile);
            logger.info("Full-page screenshot is saved due to test failure: "+testName);

            Allure.addAttachment("Test Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
        } catch (IOException e){
            logger.error("Failed to save");
        }

        
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
