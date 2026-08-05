package homework.com.ecommerce.repository;

import homework.com.ecommerce.db.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
  private DatabaseConnection db;

  public ProductRepository(DatabaseConnection db) {
    this.db = db;
  }

  public List<String> findAll(){
    return db.getProducts();
  }

  public void save(String productName) {
    db.addProduct(productName);
  }

}
