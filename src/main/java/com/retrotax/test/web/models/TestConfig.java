package com.retrotax.test.web.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@Entity
@Table(name="TEST_CONFIG")
public class TestConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "testConfig", cascade = CascadeType.ALL)
    private Capabilities capabilities;

    @OneToMany(mappedBy = "testConfig", cascade = CascadeType.ALL)
    private List<TestFile> testFiles;

    @OneToMany(mappedBy = "testConfig", cascade = CascadeType.ALL)
    private List<TestResult> results;

    @Column(name = "PAGE_URL")
    private String pageUrl;

    @Column(name = "RUNNABLE")
    private boolean run;

    public TestConfig() {
    }

    public TestConfig(final Capabilities capabilities, final List<TestFile> testFiles, final String pageUrl) {
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

    public List<TestFile> getTestFiles() {
        return testFiles;
    }

    public void setTestFiles(List<TestFile> testFiles) {
        this.testFiles = testFiles;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public List<TestResult> getResults() {
        return results;
    }

    public void setResults(List<TestResult> results) {
        this.results = results;
    }
}
