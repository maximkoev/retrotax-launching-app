package com.retrotax.test.web.tests.abstracttests;

import com.retrotax.test.web.config.*;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.net.URL;

public abstract class AbstractTest {

    protected static TestConfig CONFIG;
    protected static String PAGE_URL;

    public static void setConfig(TestConfig config) {
        AbstractTest.CONFIG = config;
        PAGE_URL = CONFIG.getPageUrl();
    }

    protected WebDriver driver;

    public abstract String getTestName();

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("platform", CONFIG.getCapabilities().getPlatform());
        capability.setCapability("browserName", CONFIG.getCapabilities().getBrowserName());
        capability.setCapability("version", CONFIG.getCapabilities().getVersion());
        capability.setCapability("resolution", CONFIG.getCapabilities().getResolution());

        capability.setCapability("build", CONFIG.getCapabilities().getBuild());
        capability.setCapability("project", CONFIG.getCapabilities().getProject());
        capability.setCapability("name", getTestName());

        capability.setCapability("browserstack.debug", CONFIG.getCapabilities().getBrowserstack_debug());
        capability.setCapability("acceptSslCerts", CONFIG.getCapabilities().getAcceptSslCerts());

        StringBuilder url = new StringBuilder("http://");
        if(CONFIG.getCapabilities().getUsername() != null) {
            url.append(CONFIG.getCapabilities().getUsername());
            url.append(":");
            url.append(CONFIG.getCapabilities().getPassword());
            url.append("@");
        }
        url.append(CONFIG.getCapabilities().getServerUrl());

        driver = new RemoteWebDriver(new URL(url.toString()), capability);
    }

    @Test
    public abstract void test() throws Exception;

    @Rule
    public TestWatcher failWatcher = new TestWatcher() {
        @Override
        protected void failed(Throwable e, Description description) {
            Client client = ClientBuilder.newClient(new ClientConfig()
                                                                .register(HttpAuthenticationFeature.basic(CONFIG.getCapabilities().getUsername(),
                                                                        CONFIG.getCapabilities().getPassword())));

            String url = "https://www.browserstack.com/automate/sessions/" +
                                        ((RemoteWebDriver)driver).getSessionId() + ".json";
            String reason = e.getMessage().split("\n")[0].replaceAll("\"", "\'");
            String data = "{\"status\":\"error\", \"reason\":\"" + reason + "\"}";

            client.target(url)
                  .request()
                  .put(Entity.json(data));
        }

        @Override
        protected void finished(Description description) {
            driver.quit();
        }
    };
}
