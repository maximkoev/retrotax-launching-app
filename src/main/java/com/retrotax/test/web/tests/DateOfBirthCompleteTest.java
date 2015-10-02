package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class DateOfBirthCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "DateOfBirthCompleteTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        driver.findElement(By.xpath("//input[@name='dob']")).click();

        assertEquals("__/__/____", driver.findElement(By.xpath("//input[@name='dob']")).getAttribute("value"));

        driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("11221963");
        assertEquals("11/22/1963", driver.findElement(By.xpath("//input[@name='dob']")).getAttribute("value"));
    }
}
