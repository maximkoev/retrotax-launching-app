package com.retrotax.test.web.config;

public class Capabilities {
    private String serverUrl;
    private String username;
    private String password;
    private String project;
    private String build;
    private boolean browserstack_debug;
    private boolean acceptSslCerts;
    private String platform;
    private String browserName;
    private String version;
    private String resolution;
    private String screenshotsPath;

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

    public boolean getBrowserstack_debug() {
        return browserstack_debug;
    }

    public void setBrowserstack_debug(boolean browserstack_debug) {
        this.browserstack_debug = browserstack_debug;
    }

    public boolean getAcceptSslCerts() {
        return acceptSslCerts;
    }

    public void setAcceptSslCerts(boolean acceptSslCerts) {
        this.acceptSslCerts = acceptSslCerts;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
