package win.hgfdodo.integration.jdbc.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;

import javax.sql.DataSource;

@SpringBootApplication
public class JdbcIntegrationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(JdbcIntegrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
