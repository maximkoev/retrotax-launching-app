package com.retrotax.test.web.tests;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalendarMiddleBtnTest extends AbstractTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.US);

    @Override
    public String getTestName() {
        return "CalendarMiddleBtnTest";
    }

    @Override
    public void test() throws Exception {
        driver.get(PAGE_URL);

        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();

        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");

        driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[4]/div/div/div/span[2]/button")).click();
        TimeUnit.MILLISECONDS.sleep(2000); //wait animation
        WebElement cal = driver.findElement(By.xpath("//div[@id='basicInfoId']/div[2]/div/div[4]/div/div/div/ul"));
        assertTrue(cal.isDisplayed());

        WebElement calTable = cal.findElement(By.tagName("table"));
        Date curDate = new Date();

        calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button")).click();
        calTable = cal.findElement(By.tagName("table"));
        Date calDate = dateFormat.parse(calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong")).getText());
        assertEquals(curDate.getYear(), calDate.getYear());

        calTable.findElement(By.cssSelector("thead tr th:nth-child(3) button")).click();
        calTable = cal.findElement(By.tagName("table"));
        calDate = dateFormat.parse(calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong")).getText());
        assertEquals(curDate.getYear() + 1, calDate.getYear());

        calTable.findElement(By.cssSelector("thead tr th:nth-child(1) button")).click();
        calTable = cal.findElement(By.tagName("table"));
        calDate = dateFormat.parse(calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong")).getText());
        assertEquals(curDate.getYear(), calDate.getYear());

        calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button")).click();
        calTable = cal.findElement(By.tagName("table"));
        String yearRange = calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong")).getText();
        int calYear1 = Integer.parseInt(yearRange.substring(0,4));
        int calYear2 = Integer.parseInt(yearRange.substring(7,11));
        int year = calDate.getYear() + 1900;
        int rest = year % 100;
        assertEquals(year - rest%20 + 1, calYear1);
        assertEquals(year + (20 - rest%20), calYear2);

        calTable.findElement(By.cssSelector("thead tr th:nth-child(3) button")).click();
        calTable = cal.findElement(By.tagName("table"));
        yearRange = calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong")).getText();
        int calYear12 = Integer.parseInt(yearRange.substring(0,4));
        int calYear22 = Integer.parseInt(yearRange.substring(7,11));
        assertEquals(calYear1 + 20, calYear12);
        assertEquals(calYear2 + 20, calYear22);

        calTable.findElement(By.cssSelector("thead tr th:nth-child(1) button")).click();
        calTable = cal.findElement(By.tagName("table"));
        yearRange = calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong")).getText();
        int calYear13 = Integer.parseInt(yearRange.substring(0,4));
        int calYear23 = Integer.parseInt(yearRange.substring(7,11));
        assertEquals(calYear1, calYear13);
        assertEquals(calYear2, calYear23);
    }
}
