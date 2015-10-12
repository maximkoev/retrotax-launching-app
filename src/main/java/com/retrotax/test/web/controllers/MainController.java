package com.retrotax.test.web.controllers;

import com.retrotax.test.web.database.*;
import com.retrotax.test.web.models.*;
import com.retrotax.test.web.security.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TestingService testingService;

    @Autowired
    private TestConfigRepository testConfigRepository;

    @Autowired
    private TestFileRepository testFileRepository;

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private TestFailureRepository testFailureRepository;

    @Autowired
    private CapabilitiesRepository testCapabilitesRepository;

    @RequestMapping(value = "/test", consumes="application/json", method = RequestMethod.POST)
    public @ResponseBody TestResult runTests(@RequestBody List<TestConfig> seleniumTests) {
        return testingService.runTests(seleniumTests);
    }

    private void doneTestConfig(final TestResult testResult, final TestConfig testConfig) {
        testResult.setTestConfig(testConfig);

        for (TestFailure testFailure : testResult.getFailures()) {
            testFailure.setTestResult(testResult);
        }

        List<TestResult> results = testConfig.getResults();
        if (results == null) {
            results = new LinkedList<>();
        }

        results.add(testResult);

        testConfig.setResults(results);
        testConfigRepository.save(testConfig);
    }

    private TestConfig persistTestConfig(TestConfig testConfig) {
        TestConfig newConfig = testConfig;

        // save capabilites
        Capabilities capabilities = newConfig.getCapabilities();
        capabilities.setTestConfig(newConfig);
        newConfig.setCapabilities(capabilities);

        // save testfiles
        for (TestFile testFile : newConfig.getTestFiles()) {
            testFile.setTestConfig(newConfig);
        }

        // save test config
        newConfig = testConfigRepository.save(newConfig);

        return newConfig;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/test", consumes = "application/json", method = RequestMethod.POST)
    public @ResponseBody TestConfig newTests(@RequestBody TestConfig testConfig) {
        final TestConfig persistConfig = persistTestConfig(testConfig);

        final List<TestConfig> list = new LinkedList<>();
        list.add(persistConfig);

        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {
                doneTestConfig(testingService.runTests(list), persistConfig);
            }
        });
        test.start();

        return persistConfig;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    public @ResponseBody List<TestConfig> all() {
        List<TestConfig> tests = new LinkedList<>();
        for (TestConfig testConfig : testConfigRepository.findAll()) {
            tests.add(testConfig);
        }

        return tests;
    }

}
