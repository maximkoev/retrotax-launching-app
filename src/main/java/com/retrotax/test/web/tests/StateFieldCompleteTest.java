package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StateFieldCompleteTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "StateFieldCompleteTest";
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
        assertEquals("Have you or has anyone living with you received Temporary Assistance to Needy Families (TANF) at any time since August 5, 1997?",
                driver.findElement(By.cssSelector("table.table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > th:nth-child(1)")).getText());
        //Step 4
        driver.findElement(By.cssSelector("table.table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > label:nth-child(2)")).click();
        assertTrue(isElementPresent(driver, By.cssSelector("div.panel:nth-child(3) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1) > span:nth-child(1)")));
        //Step 5
        driver.findElement(By.cssSelector("div.panel:nth-child(3) > div:nth-child(1) > h4:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
        //Step 6
        WebDriverWait wait_collapse = new WebDriverWait(driver, 20);
        element = wait_collapse.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".in > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2)")));
        int fontsize = Integer.parseInt(element.getCssValue("font-size").replaceAll("[\\D]", ""));
        int top = Integer.parseInt(element.getCssValue("top").replaceAll("[\\D]", ""));
        driver.findElement(By.cssSelector("#recipient_received_stateid"));
        int fontsize2 = Integer.parseInt(element.getCssValue("font-size").replaceAll("[\\D]", ""));
        int top2 = Integer.parseInt(element.getCssValue("top").replaceAll("[\\D]", ""));
        System.out.println(fontsize);
        System.out.println(fontsize2);
        System.out.println(top);
        System.out.println(top2);
        //assertTrue((fontsize > fontsize2) && (top < top2));
        //Step 7
        driver.findElement(By.cssSelector("#recipient_received_stateid > option:nth-child(1)")).click();
        System.out.println(driver.findElement(By.cssSelector("#recipient_received_stateid")).getText());
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
