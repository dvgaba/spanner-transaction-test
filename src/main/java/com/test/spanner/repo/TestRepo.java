package com.test.spanner.repo;

import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import com.test.spanner.domain.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends SpannerRepository<Test, String> {}
