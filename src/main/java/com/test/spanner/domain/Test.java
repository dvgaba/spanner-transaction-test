package com.test.spanner.domain;

import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;

@Table(name = "test")
public class Test {

  @PrimaryKey String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
