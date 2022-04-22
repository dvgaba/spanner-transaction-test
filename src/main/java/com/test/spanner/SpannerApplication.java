package com.test.spanner;

import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSpannerRepositories(basePackages = "com.test.spanner.repo")
public class SpannerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpannerApplication.class, args);
  }
}
