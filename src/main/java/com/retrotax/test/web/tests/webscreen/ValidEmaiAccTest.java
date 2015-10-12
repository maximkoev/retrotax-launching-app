package com.retrotax.test.web.tests.webscreen;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ValidEmaiAccTest extends AbstractTest {

    String loginPage, user, pass, text;

    @Before
    public void setUp() throws Exception{
        super.setUp();
        loginPage = PAGE_URL + "users/login";
        user = "justdoitAdmin";
        pass = "LetJustDoItIn";
        text = "Anything";
    }

    @Test
    public void test() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(loginPage);
        // driver.findElement(By.linkText("Login")).click();
        WebElement name = driver.findElement(By.id("user-username"));
        name.clear();
        name.sendKeys(user);
        WebElement password = driver.findElement(By.id("user-password"));
        password.clear();
        password.sendKeys(pass);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
        driver.findElement(By.id("content")).click();
        driver.findElement(By.linkText("Update Account")).click();
        WebElement email = driver.findElement(By.id("user-email"));
        email.clear();
        email.sendKeys(text);
        email.submit();
        try {
            Assert.assertEquals("(invalid email)",
                    driver.findElement(By.cssSelector("label.error")).getText());
        } catch (NoSuchElementException e) {
        }
        driver.close();
        driver.quit();
    }

    private boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return false;        }

        catch (NoSuchElementException e) {
            return true;
        }
    }

    @Override
    public String getTestName() {
        return "ValidEmaiAccTest";
    }
}

