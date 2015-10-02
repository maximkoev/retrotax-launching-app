package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class AddressAutoCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "AddressAutoCompleteTest";
    }

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

        //less than 5 characters
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys(less5);
        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();
        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");
        driver.findElement(By.xpath("//input[@name='address']")).click();
        assertEquals(less5, driver.findElement(By.xpath("//input[@name='address']")).getAttribute("value"));
        assertEquals("Address must be between 5 and 100 characters",
                     driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());

        //from 5 to 100 characters
        driver.get("http://185.65.245.157:3000/");
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys(from5to100);
        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();
        wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");
        driver.findElement(By.xpath("//input[@name='address']")).click();
        assertEquals(from5to100, driver.findElement(By.xpath("//input[@name='address']")).getAttribute("value"));
        try {
            assertEquals("", driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());
        } catch (NoSuchElementException e) {}

        //more than 100 characters
        driver.get("http://185.65.245.157:3000/");
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys(more100);
        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();
        wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");
        driver.findElement(By.xpath("//input[@name='address']")).click();
        assertEquals(more100, driver.findElement(By.xpath("//input[@name='address']")).getAttribute("value"));
        assertEquals("Address must be between 5 and 100 characters",
                     driver.findElement(By.cssSelector("input[name='address'] ~ .hint")).getText());
    }
}
