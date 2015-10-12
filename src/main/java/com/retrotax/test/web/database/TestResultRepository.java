package com.retrotax.test.web.database;

import com.retrotax.test.web.models.TestFile;
import com.retrotax.test.web.models.TestResult;
import org.springframework.data.repository.CrudRepository;

public interface TestResultRepository extends CrudRepository<TestResult, Long> {}
