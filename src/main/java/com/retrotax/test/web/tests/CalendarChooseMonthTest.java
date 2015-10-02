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
import static org.junit.Assert.fail;

public class CalendarChooseMonthTest extends AbstractTest {

    private static final DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.US);

    @Override
    public String getTestName() {
        return "CalendarChooseMonthTest";
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
        WebElement monthYear = calTable.findElement(By.cssSelector("thead tr th:nth-child(2) button strong"));
        Date curDate = new Date();
        Date calDate = dateFormat.parse(monthYear.getText());
        assertEquals(curDate.getYear(), calDate.getYear());
        assertEquals(curDate.getMonth(), calDate.getMonth());

        calTable.findElement(By.cssSelector("thead tr th:nth-child(3) button")).click();
        calDate = dateFormat.parse(monthYear.getText());
        int month = calDate.getMonth() == 0 ? 12 : calDate.getMonth();
        assertEquals(curDate.getMonth() + 1, month);
        boolean noFirstDay = true;
        for(WebElement day : calTable.findElements(By.cssSelector("tbody > tr:nth-child(1) > td > button"))) {
            if(day.findElement(By.tagName("span")).getText().equals("01")) {
                assertTrue(day.getAttribute("class").contains("active"));
                noFirstDay = false;
                break;
            }
        }
        if(noFirstDay) {
            fail();
        }

        calTable.findElement(By.cssSelector("thead tr th:nth-child(1) button")).click();
        calDate = dateFormat.parse(monthYear.getText());
        assertEquals(curDate.getMonth(), calDate.getMonth());
        noFirstDay = true;
        for(WebElement day : calTable.findElements(By.cssSelector("tbody > tr:nth-child(1) > td > button"))) {
            if(day.findElement(By.tagName("span")).getText().equals("01")) {
                assertTrue(day.getAttribute("class").contains("active"));
                noFirstDay = false;
                break;
            }
        }
        if(noFirstDay) {
            fail();
        }
    }
}
