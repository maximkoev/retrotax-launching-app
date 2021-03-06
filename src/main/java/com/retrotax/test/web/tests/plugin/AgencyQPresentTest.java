package com.retrotax.test.web.tests.plugin;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Maxym on 23/09/2015.
 */
public class AgencyQPresentTest extends AbstractTest {

    @Override
    public String getTestName() {
        return "AgencyQPresentTest";
    }

    @Override
    public void test(){
        driver.get(PAGE_URL);
        driver.findElement(By.xpath("//button[@id=\"retrotax_plugin_trigger\"]")).click();
        WebDriverWait wait_iframe = new WebDriverWait(driver, 20);
        wait_iframe.until(ExpectedConditions.visibilityOfElementLocated(By.id("_bftn_iframe")));
        driver.switchTo().frame("_bftn_iframe");
        driver.findElement(By.xpath("//form[@id='frmEmployee']/accordion/div/div[2]/div/h4/a/span")).click();
        driver.findElement(By.xpath("//tr[@id='tr_vocrehab']/td/div/label[2]")).click();
        driver.findElement(By.xpath("//form[@id='frmEmployee']/accordion/div/div[4]/div/h4/a/span")).click();
        Assert.assertEquals("Received vocational rehabilitation services from a vocational rehabilitation agency?",
                driver.findElement(By.xpath("//form[@id='frmEmployee']/accordion/div/div[4]/div[2]/div/table/tbody/tr/th")).getText());

    }

}



