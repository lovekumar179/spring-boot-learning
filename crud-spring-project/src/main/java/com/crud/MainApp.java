package com.crud;

import com.crud.config.AppConfig;
import com.crud.controller.UserController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserController controller = context.getBean(UserController.class);

    controller.createUser("Sham Sundar");
    controller.createUser("Heeralal");

    controller.listUser();

  }
}
