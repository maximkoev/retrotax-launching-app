package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by User on 23.09.2015.
 */
public class ZipTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "ZipTest";
    }

    @Override
    public void test() throws Exception {
        driver.get(PAGE_URL);
        driver.findElement(By.xpath("//*[@id=\"retrotax_plugin_trigger\"]")).click();
        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");
        driver.findElement(By.id("zip")).clear();
        driver.findElement(By.id("zip")).sendKeys("1");
        Thread.sleep(1000);
        Assert.assertEquals("1", driver.findElement(By.id("zip")).getAttribute("value"));
        System.out.println("ZipTes passed");
        driver.quit();
    }
}
