package homework.com.ecommerce.service;

import homework.com.ecommerce.repository.ProductRepository;
import homework.com.ecommerce.service.payment.PaymentGateway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
  private ProductRepository productRepository;

//  @Qualifier("paypalPayment")
  private PaymentGateway paymentGateway;

  public ProductService(ProductRepository productRepository, PaymentGateway paymentGateway) {
    this.productRepository = productRepository;
    this.paymentGateway = paymentGateway;
  }

  public void addProduct(String name) {
    productRepository.save(name);
  }

  public List<String> getAllProduct() {
    return productRepository.findAll();
  }

  public void doPayment(double amount){
    System.out.println(paymentGateway.pay(amount));
  }

}
