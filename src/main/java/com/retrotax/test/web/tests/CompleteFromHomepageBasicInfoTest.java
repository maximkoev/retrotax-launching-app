package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class CompleteFromHomepageBasicInfoTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "CompleteFromHomepageBasicInfoTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("John");
        driver.findElement(By.xpath("//input[@id='last_name']")).sendKeys("Doe");

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        assertEquals("Basic Info: John Doe", driver.findElement(By.xpath("//div[@id='basicInfoId']/div[1]/h4/a/span")).getText());
        assertEquals("John", driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value"));
        assertEquals("Doe", driver.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value"));
    }
}
