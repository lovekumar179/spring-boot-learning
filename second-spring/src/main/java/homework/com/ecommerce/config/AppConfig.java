package homework.com.ecommerce.config;

import homework.com.ecommerce.db.DatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("homework.com.ecommerce")
public class AppConfig {
  @Bean(initMethod = "init", destroyMethod = "cleanup")
  public DatabaseConnection dbConnection() {
    return new DatabaseConnection();
  }
}
