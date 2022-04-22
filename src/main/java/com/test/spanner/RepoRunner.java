package com.test.spanner;

import com.test.spanner.domain.Test;
import com.test.spanner.repo.TestRepo;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoRunner {

  @Autowired private TestRepo testRepo;

  @PostConstruct
  public void doOps() {
    System.out.println();
    testRepo.performReadWriteTransaction(
        (testRepo) -> {
          Test t = new Test();
          String id = UUID.randomUUID().toString();
          t.setId(id);
          testRepo.save(t);
          System.out.println(
              " ==== ==== ==== ==== ==== ==== ====from db via REPO  ==== ==== ==== ==== ===="
                  + testRepo.findById(id));
          return "Done";
        });
  }
}
