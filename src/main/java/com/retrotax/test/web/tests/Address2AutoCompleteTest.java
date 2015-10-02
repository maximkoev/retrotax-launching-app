package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Address2AutoCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "Address2AutoCompleteTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//input[@id='address2']")).sendKeys("Address2");

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        assertEquals("Address2", driver.findElement(By.xpath("//input[@name='address2']")).getAttribute("value"));

        driver.findElement(By.xpath("//input[@name='address2']")).click();
        assertEquals("Enter additional address information (such as a suite number, floor number, building name, or P.O. Box)",
                driver.findElement(By.cssSelector("input[name='address2'] ~ .hint")).getText());
        assertTrue(driver.findElement(By.cssSelector("input[name='address2'] ~ .hint")).isDisplayed());
    }
}
