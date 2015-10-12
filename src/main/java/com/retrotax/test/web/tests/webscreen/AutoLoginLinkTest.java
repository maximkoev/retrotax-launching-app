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
 * Created by Maxym on 07/10/2015.
 */
public class AutoLoginLinkTest extends AbstractTest {
    String base_URL;
    String user;
    String pass, text, SwitychToAdmin, gotoUsers, mail,LoginLink,CurrentLink;

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
        Email.submit();
        LoginLink = driver.findElement(By.id("LoginLink")).getText();
        driver.get(LoginLink);
        CurrentLink = driver.getCurrentUrl();
        if (LoginLink != CurrentLink){
            System.out.println("LoginUrl: " + LoginLink);
            System.out.println("CurrentURL: " + CurrentLink);
        }
        Assert.assertEquals(LoginLink, CurrentLink);
    }

    @Override
    public String getTestName() {
        return "AutoLoginLinkTest";
    }
}
