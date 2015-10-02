package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class RecipientRelationshipFieldCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "RecipientRelationshipFieldCompleteTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        driver.findElement(By.xpath("//form/accordion/div/div[2]/div[1]/h4/a")).click();

        assertTrue(driver.findElement(By.xpath("//form/accordion/div/div[2]/div[2]")).isDisplayed());

        driver.findElement(By.xpath("//form/accordion/div/div[2]/div[2]/div/table/tbody/tr[1]/td/div/label[2]")).click();

        assertTrue(driver.findElement(By.xpath("//form/accordion/div/div[3]/div[1]")).isDisplayed());

        driver.findElement(By.xpath("//form/accordion/div/div[3]/div[1]/h4/a")).click();

        assertTrue(driver.findElement(By.xpath("//form/accordion/div/div[3]/div[2]")).isDisplayed());
        assertFalse(driver.findElement(By.xpath("//form/accordion/div/div[2]/div[2]")).isDisplayed());


        driver.findElement(By.xpath("//input[@name='recipient_relationship']")).click();
        assertEquals("Recipient Relationship is required",
                driver.findElement(By.cssSelector("input[name='recipient_relationship'] ~ .hint")).getText());
        assertTrue(driver.findElement(By.cssSelector("input[name='recipient_relationship'] ~ .hint")).isDisplayed());

        driver.findElement(By.xpath("//input[@name='recipient_relationship']")).sendKeys("A");
        assertEquals("A", driver.findElement(By.xpath("//input[@name='recipient_relationship']")).getAttribute("value"));
        try {
            assertEquals("", driver.findElement(By.cssSelector("input[name='recipient_relationship'] ~ .hint")).getText());
        } catch (NoSuchElementException e) {}
    }
}
