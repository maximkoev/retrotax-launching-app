package com.retrotax.test.web.tests.webscreen;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Maxym on 08/10/2015.
 */
public class ATCAccCheckboxTest extends AbstractTest {
    String base_URL;
    String user;
    String pass, text, SwitychToAdmin, gotoUsers, mail, LoginLink, CurrentLink;

    @Test
    public void test() throws InterruptedException {
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
        password.submit();
        driver.get(SwitychToAdmin);
        driver.get(gotoUsers);
        driver.findElement(By.id("new_user")).click();
        // fill out all fields
        WebElement Username = driver.findElement(By.id("user-username"));
        WebElement First_Name = driver.findElement(By.id("user-firstname"));
        WebElement Last_Name = driver.findElement(By.id("user-lastname"));
        WebElement ConfirmPassword = driver.findElement(By.id("user-passwordConfirmation"));
        WebElement Password = driver.findElement(By.id("user-password"));
        WebElement Email = driver.findElement(By.id("user-email"));
        Username.clear();
        First_Name.clear();
        First_Name.sendKeys(user);
        Last_Name.clear();
        Last_Name.sendKeys(user);
        ConfirmPassword.clear();
        ConfirmPassword.sendKeys(pass);
        Password.clear();
        Password.sendKeys(pass);
        Username.sendKeys("user");
        Email.clear();
        Email.sendKeys(mail);
        WebElement ATScheckbox = driver.findElement(By.id("user-ats"));
        ATScheckbox.isEnabled();
        Email.submit();



    try{
        Assert.assertEquals("ATS Account Settings",
                driver.findElement(By.cssSelector("CssOfText")));
    }
    catch (NoSuchElementException e){
    }


    }

    @Override
    public String getTestName() {
        return "ATCAccCheckboxTest";
    }
}
