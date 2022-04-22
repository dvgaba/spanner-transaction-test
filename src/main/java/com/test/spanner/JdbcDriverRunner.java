package com.test.spanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class JdbcDriverRunner {

  @Autowired private Environment environment;

  @PostConstruct
  public void doOps() throws Exception {
    String connectionUrl =
        String.format(
            "jdbc:cloudspanner://localhost:9010/projects/%s/instances/%s/databases/%s?usePlainText=true",
            environment.getProperty("spring.cloud.gcp.spanner.project-id"),
            environment.getProperty("spring.cloud.gcp.spanner.instance-id"),
            environment.getProperty("spring.cloud.gcp.spanner.database"));
    try (Connection connection = DriverManager.getConnection(connectionUrl)) {
      String id = UUID.randomUUID().toString();
      connection.setAutoCommit(false);
      try (PreparedStatement ps =
          connection.prepareStatement(
              "INSERT INTO Test\n" + "(id)\n" + "VALUES\n" + "('" + id + "')")) {
        int updateCounts = ps.executeUpdate();
        System.out.printf("Insert counts: %s%n", updateCounts);
      }
      Statement statement = connection.createStatement();
      try (ResultSet rs = statement.executeQuery("SELECT * FROM test where id = '" + id + "'")) {
        while (rs.next()) {
          System.out.println(
              " ==== ==== ==== ==== ==== from db via JDBC  ==== ==== ==== ==== ===="
                  + rs.getString(1));
        }
      }
      connection.commit();
    } finally {

    }
  }
}
