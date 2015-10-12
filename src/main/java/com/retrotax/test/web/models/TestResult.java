package com.retrotax.test.web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TEST_RESULT")
public class TestResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "testResult", cascade = CascadeType.ALL)
    private List<TestFailure> failures;

    @ManyToOne
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private TestConfig testConfig;

    @Column(name = "RUN_COUNT")
    private int testsRun;

    @Column(name = "FAILED_COUNT")
    private int testsFailed;

    public TestResult() {

    }

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

    public TestConfig getTestConfig() {
        return testConfig;
    }

    public void setTestConfig(TestConfig testConfig) {
        this.testConfig = testConfig;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
