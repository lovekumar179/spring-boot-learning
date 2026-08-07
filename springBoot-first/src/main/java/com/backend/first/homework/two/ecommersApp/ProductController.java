package com.backend.first.homework.two.ecommersApp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
  Map<Integer, Product> ecomDb = new HashMap<>();

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProduct (@PathVariable int id){
    if (!ecomDb.containsKey(id))
      return ResponseEntity.notFound().build();
    return ResponseEntity.ok(ecomDb.get(id));
  }

  @GetMapping
  public ResponseEntity<List<Product>> searchProducts(
      @RequestParam(required = false, defaultValue = "beauty") String category,
      @RequestParam(required = false, defaultValue = "1000") double minPrice
  ) {
    System.out.println(category);
    List<Product> products = ecomDb.values().stream()
        .filter(u -> u.getCategory().equalsIgnoreCase(category))
        .filter(u -> u.getMinPrice() >= minPrice)
        .toList();
    return ResponseEntity.ok(products);
  }


  @GetMapping("/users/{userId}/orders/{orderId}")
  public ResponseEntity<String> getUserOrder(
      @PathVariable("userId") int id,
      @PathVariable int orderId
  ) {
    System.out.println("ORDER ID: " + orderId);

    return ResponseEntity.ok("UserID: "+id+" OrderID: "+orderId);
  }

  @GetMapping("/product/{id}")
  public String getInfo(
      @PathVariable String category,
      @RequestParam double minPrice,
      @RequestHeader("Authorization") String token
  ) {
    return "AUTH DETAILS: " + token
        + " : " + category
        + " : " + minPrice;
  }

}
