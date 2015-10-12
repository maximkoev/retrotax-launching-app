package com.retrotax.test.web.database;

import com.retrotax.test.web.models.TestConfig;
import com.retrotax.test.web.models.TestFile;
import org.springframework.data.repository.CrudRepository;

public interface TestFileRepository extends CrudRepository<TestFile, Long> {}