package com.backend.first.homework.two.ecommersApp;

public class Product {
  private int id;
  private String name;
  private String category;
  private double minPrice;

  public Product(int id, String name, String category, double minPrice) {
    this.id = id;
    this.name = name;
    this.category = category;
    this.minPrice = minPrice;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public double getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(double minPrice) {
    this.minPrice = minPrice;
  }
}
