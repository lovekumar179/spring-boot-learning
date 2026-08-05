package homework.com.ecommerce.controller;

import homework.com.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  public void createProduct(String name) {
    productService.addProduct(name);
    System.out.println("User created: "+name);
  }

  public void listProduct() {
    List<String> users = productService.getAllProduct();
    System.out.println("All users: "+users);
  }

  public void payment(double amount){
    productService.doPayment(amount);
  }

}
