package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class VerifyAllErrorMessageTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "VerifyAllErrorMessageTest";
    }

    @Override
    public void test() {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id='retrotax_plugin_trigger']")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        driver.findElement(By.xpath("//*[@id=\"btnSave\"]")).click();

        assertEquals("First Name is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[7]/div/span")).getText());
        assertEquals("Last Name is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[8]/div/span")).getText());
        assertEquals("Address is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[9]/div/span")).getText());
        assertEquals("Zip is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[10]/div/span")).getText());
        assertEquals("City is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[11]/div/span")).getText());
        assertEquals("Date of Birth is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[12]/div/span")).getText());
        assertEquals("Email Address is required",
                     driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[13]/div/span")).getText());
    }
}
