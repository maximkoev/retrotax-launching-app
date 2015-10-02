package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class VacationRehubBtnTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "VacationRehubBtnTest";
    }

    @Override
    public void test() {
        WebElement element;

        driver.get(PAGE_URL);
        //Step 1
        driver.findElement(By.xpath("//button[@id='retrotax_plugin_trigger']")).click();
        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");
        //Step 2
        driver.findElement(By.cssSelector("div.panel:nth-child(2) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
        //Step 3
        assertTrue(isElementPresent(driver, By.cssSelector("#tr_vocrehab > th:nth-child(1)")));
        //Step 4
        driver.findElement(By.cssSelector("#tr_vocrehab > td:nth-child(2) > div:nth-child(1) > label:nth-child(2)")).click();
        element = driver.findElement(By.cssSelector("div.panel:nth-child(4) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1) > span:nth-child(1)"));
        assertNotNull(element);
        //Step 5
        element.click();
        assertTrue(isElementPresent(driver, By.cssSelector("div.panel:nth-child(4)")));
    }

    private boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
