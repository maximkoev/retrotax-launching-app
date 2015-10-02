package com.retrotax.test.web.config;

import java.util.List;

public class TestConfig {
    private Capabilities capabilities;
    private List<String> testFiles;
    private String pageUrl;

    public TestConfig() {
    }

    public TestConfig(Capabilities capabilities, List<String> testFiles, String pageUrl) {
        this.capabilities = capabilities;
        this.testFiles = testFiles;
        this.pageUrl = pageUrl;
    }

    public Capabilities getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Capabilities capabilities) {
        this.capabilities = capabilities;
    }

    public List<String> getTestFiles() {
        return testFiles;
    }

    public void setTestFiles(List<String> testFiles) {
        this.testFiles = testFiles;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
