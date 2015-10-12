package com.retrotax.test.web.tests.webscreen;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class MatchingPassNewUserTest extends AbstractTest {

    String pass, text, adminPage, usersPage, mail, user, loginPage;

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
    public void test ()throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(loginPage);
        //driver.findElement(By.linkText("Login")).click();
        WebElement name = driver.findElement(By.id("user-username"));
        name.clear();
        name.sendKeys(user);
        WebElement password = driver.findElement(By.id("user-password"));
        password.clear();
        password.sendKeys(pass);
        name.submit();
        //driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.get(adminPage);
        driver.get(usersPage);
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
