package com.retrotax.test.web.tests.webscreen;

import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;


public class ForgotPasswordTest extends AbstractTest {

    String loginPage, user, pass, text, adminPage, usersPage, mail;

    @Before
    public void setUp() throws Exception {
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
    public void test(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(loginPage);
        WebElement ForgotPass = driver.findElement(By.linkText("exact:Lost Password?"));
        ForgotPass.click();

        try {
            Assert.assertEquals("Missing your password?",
                    driver.findElement(By.xpath("//*[@id=\"client\"]/h1")).getText());
        }
            catch(NullPointerException e){
            }


    }
    public String getTestName(){return "ForgotPasswordTest";}
}

