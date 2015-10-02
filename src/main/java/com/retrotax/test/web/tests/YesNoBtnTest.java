package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class YesNoBtnTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "YesNoBtnTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        assertEquals("NO", driver.findElement(By.xpath("//div[@id='rehire']/div/label[1]")).getText());
        assertTrue(driver.findElement(By.xpath("//div[@id='rehire']/div/label[1]")).getAttribute("class").contains("active"));

        driver.findElement(By.xpath("//div[@id='rehire']/div/label[2]")).click();
        assertEquals("YES", driver.findElement(By.xpath("//div[@id='rehire']/div/label[2]")).getText());
        assertTrue(driver.findElement(By.xpath("//div[@id='rehire']/div/label[2]")).getAttribute("class").contains("active"));

        assertFalse(driver.findElement(By.xpath("//form/accordion/div/div[2]")).isDisplayed());
    }
}