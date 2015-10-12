package com.retrotax.test.web.tests.webscreen;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class AutoLoginLinkTest extends AbstractTest {

    String loginPage, user, pass, text, adminPage, usersPage, mail, LoginLink, CurrentLink;

    @Before
    public void setUp() throws Exception{
        super.setUp();
        loginPage = PAGE_URL + "users/login";
        user = "justdoitAdmin";
        pass = "LetJustDoItIn";
        text = "Anything";
        adminPage = PAGE_URL + "admin/index";
        usersPage = PAGE_URL + "users/index";
        mail = "just@do-it.co";
    }

    @Test
    public void test() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(loginPage);
        //driver.findElement(By.linkText("Login")).click();
        WebElement name = driver.findElement(By.id("user-username"));
        name.clear();
        name.sendKeys(user);
        WebElement password = driver.findElement(By.id("user-password"));
        password.clear();
        password.sendKeys(pass);
        password.submit();
        driver.get(adminPage);
        driver.get(usersPage);
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
