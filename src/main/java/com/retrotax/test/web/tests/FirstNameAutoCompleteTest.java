package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class FirstNameAutoCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "FirstNameAutoCompleteTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//input[@id='first_name']")).sendKeys("John");

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        assertEquals("John", driver.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value"));
    }
}
