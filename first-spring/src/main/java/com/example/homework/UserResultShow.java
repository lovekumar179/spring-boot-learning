package com.example.homework;

public class UserResultShow {
  public Calculator calculator;

  public UserResultShow(Calculator calculator) {
    this.calculator = calculator;
  }

  public int showResult(int a, int b){
    return calculator.perform(a, b);
  }

}
