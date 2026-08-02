package com.example.demo;

import com.example.looseCoupling.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
  public static void main(String[] args) {
    // learnt to fetch bean with annotations
//    ApplicationContext context
//        = new AnnotationConfigApplicationContext(AppConfig.class);

//    GreetingService greetingService
//      = (GreetingService) context.getBean("myBean");
//
//    GreetingService greetingService
//      = context.getBean(GreetingService.class);
//    greetingService.sayHello();

//    UserService userServiceEmail = (UserService) context.getBean("UserServiceEmail");
//    UserService userServiceEmail = context.getBean(UserService.class);
//    userServiceEmail.notifyUser("What's up!..");

    /*
    UserService userServiceSMS = (UserService) context.getBean("UserServiceSMS");
    userServiceSMS.notifyUser("What's up!..");
*/

    System.out.println("---> Starting Spring Application Context...! <---");

    ApplicationContext context
      = new AnnotationConfigApplicationContext(AppConfig.class);

    System.out.println("---> Retrieving Bean Lifecycle...! <---");
    BeanLifecycle beanLifecycle = context.getBean(BeanLifecycle.class);

    beanLifecycle.performTask();
    System.out.println("---> Closing Spring Context ...! <---");



  }
}
