package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class CalendarClickTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "CalendarClickTest";
    }

    @Override
    public void test() throws InterruptedException {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[4]/div/div/div/span[2]/button")).click();
        TimeUnit.MILLISECONDS.sleep(2000); //wait animation

        assertTrue(driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[4]/div/div/div/ul")).isDisplayed());
    }
}
