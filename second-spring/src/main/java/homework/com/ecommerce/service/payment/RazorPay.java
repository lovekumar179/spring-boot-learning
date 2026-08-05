package homework.com.ecommerce.service.payment;

import org.springframework.stereotype.Component;

@Component("razorPayPayment")
public class RazorPay implements PaymentGateway{
  
  @Override
  public String pay(double amount) {
    return "✅ Paid $" + amount + " using RazorPay payment service...!";
  }
}
