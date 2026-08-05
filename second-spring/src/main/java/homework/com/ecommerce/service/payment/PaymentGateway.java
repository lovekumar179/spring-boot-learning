package homework.com.ecommerce.service.payment;

public interface PaymentGateway {
  String pay(double amount);
}
