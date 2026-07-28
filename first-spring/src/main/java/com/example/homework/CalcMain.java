package com.example.homework;

import com.example.looseCoupling.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {
  public Addition addition;
  public Subtraction subtraction;

  public CalcMain(Addition addition, Subtraction subtraction) {
    this.addition = addition;
    this.subtraction = subtraction;
  }

  public CalcMain() {
  }

  public static void main(String[] args) {
    ApplicationContext context
        = new ClassPathXmlApplicationContext("applicationBeanContext.xml");

    UserResultShow calcAdd = (UserResultShow) context.getBean("Add");
    System.out.println(calcAdd.showResult(2, 3));

    UserResultShow calcSubtract = (UserResultShow) context.getBean("Subtract");
    System.out.println(calcSubtract.showResult(2, 3));
    
  }
}
