package com.retrotax.test.web.database;

import com.retrotax.test.web.models.Capabilities;
import com.retrotax.test.web.models.TestConfig;
import org.springframework.data.repository.CrudRepository;

public interface CapabilitiesRepository extends CrudRepository<Capabilities, Long> {}