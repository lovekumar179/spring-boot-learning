package com.crud.config;

import com.crud.db.DatabaseConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.crud")
public class AppConfig {

  @Bean(initMethod = "init", destroyMethod = "cleanup")
  public DatabaseConnection dbConnection() {
    return new DatabaseConnection();
  }

}
