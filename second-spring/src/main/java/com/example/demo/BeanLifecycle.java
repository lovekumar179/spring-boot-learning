package com.example.demo;

import com.example.looseCoupling.NotificationService;
import org.springframework.stereotype.Component;

//@Component  // because we have used the @Bean annotation 
public class BeanLifecycle {

  private NotificationService notificationService;

  public BeanLifecycle(NotificationService notificationService) {
    System.out.println("Constructor Called: Dependency Injected");
    this.notificationService = notificationService;
  }

  public void init(){
    System.out.println("init Called: Bean initialized");
    notificationService.send("Hello from init()");
  }

  public void performTask(){System.out.println("Ready for use...!");}

  public void cleanup() {System.out.println("cleanup() is being called...!");}

}
