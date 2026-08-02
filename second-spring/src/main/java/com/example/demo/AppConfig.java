package com.example.demo;

import com.example.looseCoupling.NotificationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example")
public class AppConfig {

  @Bean(initMethod = "init", destroyMethod = "cleanup")
  public BeanLifecycle beanLifecycle(NotificationService notificationService) {
    return new BeanLifecycle(notificationService);
  } // manually manually creating beans



}
