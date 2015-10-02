package com.retrotax.test.web.service;

import java.util.List;

public class TestResult {

    private int testsRun;
    private int testsFailed;
    private List<TestFailure> failures;

    public TestResult(int testsRun, int testsFailed, List<TestFailure> failures) {
        this.testsRun = testsRun;
        this.testsFailed = testsFailed;
        this.failures = failures;
    }

    public int getTestsRun() {
        return testsRun;
    }

    public void setTestsRun(int testsRun) {
        this.testsRun = testsRun;
    }

    public int getTestsFailed() {
        return testsFailed;
    }

    public void setTestsFailed(int testsFailed) {
        this.testsFailed = testsFailed;
    }

    public List<TestFailure> getFailures() {
        return failures;
    }

    public void setFailures(List<TestFailure> failures) {
        this.failures = failures;
    }
}
