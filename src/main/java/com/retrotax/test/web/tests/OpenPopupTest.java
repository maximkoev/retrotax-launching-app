package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class OpenPopupTest extends AbstractTest {

    public static String SCREEN_PATH;

    @BeforeClass
    public static void loadScreenPathProp() throws IOException{
        SCREEN_PATH = CONFIG.getCapabilities().getScreenshotsPath();
    }

    @Override
    public String getTestName() {
        return "OpenPopupTest";
    }

    @Override
    public void test() throws Exception {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id='retrotax_plugin_trigger']")).click();
        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        TimeUnit.MILLISECONDS.sleep(1000); //wait for popup animation

        takeScreenshot(driver);
    }

    void takeScreenshot(WebDriver driver) throws IOException {
        driver = new Augmenter().augment(driver);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(SCREEN_PATH + "OpenPopup_" +
                                                           CONFIG.getCapabilities().getPlatform() + "_" +
                                                           CONFIG.getCapabilities().getBrowserName() + CONFIG.getCapabilities().getVersion() + "_" +
                                                           CONFIG.getCapabilities().getResolution() + ".png"));
    }
}