package com.retrotax.test.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="TEST_CAPABILITIES")
public class Capabilities implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private TestConfig testConfig;

    @Column(name="SERVER_URL")
    private String serverUrl;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="PROJECT")
    private String project;

    @Column(name="BUILD")
    private String build;

    @Column(name="BROWSERSTACK_DEBUG")
    private boolean browserstackDebug;

    @Column(name="ACCEPT_SSL_CERTS")
    private boolean acceptSslCerts;

    @Column(name="OS")
    private String os;

    @Column(name="OS_VERSION")
    private String osVersion;

    @Column(name="BROWSER")
    private String browser;

    @Column(name="BROWSER_VERSION")
    private String browserVersion;

    @Column(name="RESOLUTION")
    private String resolution;

    @Column(name="SCREENSHOTS_PATH")
    private String screenshotsPath;

    public Capabilities() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TestConfig getTestConfig() {
        return testConfig;
    }

    public void setTestConfig(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public boolean isBrowserstackDebug() {
        return browserstackDebug;
    }

    public void setBrowserstackDebug(boolean browserstackDebug) {
        this.browserstackDebug = browserstackDebug;
    }

    public boolean isAcceptSslCerts() {
        return acceptSslCerts;
    }

    public void setAcceptSslCerts(boolean acceptSslCerts) {
        this.acceptSslCerts = acceptSslCerts;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getScreenshotsPath() {
        return screenshotsPath;
    }

    public void setScreenshotsPath(String screenshotsPath) {
        this.screenshotsPath = screenshotsPath;
    }
}
