package homework.com.ecommerce.service.payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("paypalPayment")
public class Paypal implements PaymentGateway{
  @Override
  public String pay(double amount) {
    return "✅ Paid $" + amount + " using PayPal (user@example.com)";
  }
}
