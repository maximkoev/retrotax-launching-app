package com.retrotax.test.web.service;

import com.retrotax.test.web.config.TestConfig;
import com.retrotax.test.web.tests.abstracttests.AbstractTest;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

@Service
public class TestingService {

    private ClassLoader classLoader;

    @Autowired
    public TestingService(@Value("${test.classes.path}")String path) {
        try {
            classLoader = new URLClassLoader(new URL[]{new File(path).toURI().toURL()}, getClass().getClassLoader());
        } catch (MalformedURLException e) {
            throw new ExceptionInInitializerError();
        }
    }

    public TestResult runTests(List<TestConfig> configs) {
        int testsRun = 0;
        List<TestFailure> failures = new ArrayList<>();

        for(TestConfig config : configs) {
            AbstractTest.setConfig(config);

            List<Class> testClasses = new ArrayList<>();
            for(String testClass : config.getTestFiles()) {
                try {
                    testClasses.add(classLoader.loadClass("com.retrotax.test.web.tests." + testClass));
                } catch (ClassNotFoundException e) {
                    failures.add(new TestFailure(testClass, "Test class not found"));
                }
            }

            Result result = JUnitCore.runClasses(ParallelComputer.classes(), testClasses.toArray(new Class[0]));

            testsRun += result.getRunCount();

            Set<String> distinctFailures = new HashSet<String>(result.getFailureCount());
            for (Failure failure : result.getFailures()) {
                if(!distinctFailures.contains(failure.getTestHeader())) {
                    distinctFailures.add(failure.getTestHeader());
                    failures.add(new TestFailure(failure.getTestHeader(), failure.getMessage()));
                }
            }
        }

        return new TestResult(testsRun, failures.size(), failures);
    }
}
