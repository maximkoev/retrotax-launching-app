package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class DeleteErrMessageTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "DeleteErrMessageTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id='retrotax_plugin_trigger']")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click(); //works everywhere but IE
        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).sendKeys(Keys.ENTER); //works everywhere but Safari

        assertEquals("First Name is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[7]/div/span")).getText());

        driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[7]/button")).click();

        try {
            assertNotEquals("First Name is required",
                             driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[7]/div/span")).getText());
        } catch (ElementNotFoundException e) {}

    }
}
