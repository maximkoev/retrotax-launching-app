package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class AddressCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "AddressCompleteTest";
    }

    @Override
    public void test() {
        String less5 = "A";
        String from5to100 = "AAAAAA";
        String more100;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 101; i++) {
            sb.append("A");
        }
        more100 = sb.toString();

        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        WebElement input = driver.findElement(By.xpath("//input[@name='address']"));

        input.click();

        assertEquals("Address is required",
                driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());
        assertTrue(driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).isDisplayed());

        input.sendKeys(less5);
        assertEquals(less5, driver.findElement(By.xpath("//input[@name='address']")).getAttribute("value"));
        assertEquals("Address must be between 5 and 100 characters",
                     driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());

        input.clear();
        input.sendKeys(from5to100);
        assertEquals(from5to100, driver.findElement(By.xpath("//input[@name='address']")).getAttribute("value"));
        try {
            assertEquals("", driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());
        } catch (NoSuchElementException e) {}

        input.clear();
        input.sendKeys(more100);
        assertEquals(more100, driver.findElement(By.xpath("//input[@name='address']")).getAttribute("value"));
        assertEquals("Address must be between 5 and 100 characters",
                     driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());
    }
}
