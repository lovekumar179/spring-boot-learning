package com.example.demo;

import com.example.looseCoupling.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
  public static void main(String[] args) {
    // learnt to fetch bean
    ApplicationContext context
        = new ClassPathXmlApplicationContext("applicationBeanContext.xml");
    GreetingService greetingService
        = (GreetingService) context.getBean("myBean");
    greetingService.sayHello();

    // doing task
    UserService userServiceEmail = (UserService) context.getBean("UserServiceEmail");
    userServiceEmail.notifyUser("What's up!..");

    UserService userServiceSMS = (UserService) context.getBean("UserServiceSMS");
    userServiceSMS.notifyUser("What's up!..");

  }
}
