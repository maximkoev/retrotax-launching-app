package com.retrotax.test.web.tests.webscreen;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Maxym on 08/10/2015.
 */
public class MatchingPassNewUserTest extends AbstractTest {
    String base_URL;
    String user;
    String pass, text, SwitychToAdmin, gotoUsers, mail;

    @Test
    public void test ()throws InterruptedException {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(base_URL);
        //driver.findElement(By.linkText("Login")).click();
        WebElement name = driver.findElement(By.id("user-username"));
        name.clear();
        name.sendKeys(user);
        WebElement password = driver.findElement(By.id("user-password"));
        password.clear();
        password.sendKeys(pass);
        name.submit();
        //driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.get(SwitychToAdmin);
        driver.get(gotoUsers);
        driver.findElement(By.id("new_user")).click();
        WebElement Username = driver.findElement(By.id("user-username"));
        WebElement First_Name = driver.findElement(By.id("user-firstname"));
        WebElement ConfirmPassword = driver.findElement(By.id("user-passwordConfirmation"));
        WebElement Email = driver.findElement(By.id("user-email"));
        Username.clear();
        First_Name.clear();
        ConfirmPassword.clear();
        Username.sendKeys(user);
        First_Name.sendKeys("validValue");
        Email.clear();
        Email.sendKeys(mail);
        ConfirmPassword.sendKeys(text);
        ConfirmPassword.submit();
        Assert.assertEquals("(passwords must match)",
        driver.findElement(By.xpath("//form[@id='frmEdit']/span[5]/label[2]")).getText());
    }

    @Override
    public String getTestName() {
        return "MatchingPassNewUserTest";
    }
}
