package homework.com.ecommerce.db;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
  private List<String> product;

  public void init() {
    product = new ArrayList<>();
    System.out.println("DB Connected (simulated)");
  }

  public List<String> getProducts() {
    return product;
  }

  public void addProduct(String productName) {
    product.add(productName);
  }

  public void cleanup(){
    System.out.println("DB close (simulated)");
  }

}
