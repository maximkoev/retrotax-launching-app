package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class StateAutoCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "StateAutoCompleteTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//input[@id='state']")).sendKeys("CALIFORNIA");

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        assertEquals("CALIFORNIA", new Select(driver.findElement(By.xpath("//select[@name='stateid']"))).getFirstSelectedOption().getText());
    }
}
