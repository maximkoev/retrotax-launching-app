package com.retrotax.test.web.tests.webscreen;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by Maxym on 15/10/2015.
 */
public class ATVDownloadTest {
    String url, page, employees, user, pass;
    private static String downloadPath ="C:\\Users\\Maxym\\Downloads";
    private WebDriver driver;
    @Before
    public  void setUp() throws Exception{
        url = "http://dev.retrotax-aci.com";
        page = url + "/users/login";
        employees = url + "/employees/view/747951";
        user = "justdoitAdmin";
        pass = "LetJustDoItIn";
        driver = new FirefoxDriver(firefoxProfile());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(page);
        // Authorization
        WebElement name = driver.findElement(By.id("user-username"));
        name.clear();
        name.sendKeys(user);
        WebElement password = driver.findElement(By.id("user-password"));
        password.clear();
        password.sendKeys(pass);
        password.submit();
        // Authorization

    }

    @Test
    public  void test() throws Exception {


        driver.get(employees);
        driver.findElement(By.linkText("Download ATV")).click();
        Assert.assertTrue(isFileDownloaded(downloadPath, "747951-atv.pdf"));

    }
    public static FirefoxProfile firefoxProfile() throws Exception {

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("browser.download.folderList",2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
        firefoxProfile.setPreference("browser.download.dir",downloadPath);
        // Use this to disable Acrobat plugin for previewing PDFs in Firefox (if you have Adobe reader installed on your computer)
        firefoxProfile.setPreference("plugin.scan.Acrobat", "99.0");
        firefoxProfile.setPreference("plugin.scan.plid.all", false);

        return firefoxProfile;
    }
    public boolean isFileDownloaded(String downloadPath, String fileName) {
        boolean flag = false;
        File dir = new File(downloadPath);
        File[] dir_contents = dir.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                return flag=true;
        }

        return flag;
    }
}
