package com.retrotax.test.web.database;

import com.retrotax.test.web.models.TestFailure;
import com.retrotax.test.web.models.TestFile;
import org.springframework.data.repository.CrudRepository;

public interface TestFailureRepository extends CrudRepository<TestFailure, Long> {}