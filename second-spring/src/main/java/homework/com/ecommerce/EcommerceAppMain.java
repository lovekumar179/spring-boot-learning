package homework.com.ecommerce;

import homework.com.ecommerce.config.AppConfig;
import homework.com.ecommerce.controller.ProductController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EcommerceAppMain {
  public static void main(String[] args) {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    ProductController controller = context.getBean(ProductController.class);

    controller.createProduct("Head and shoulder's shampoo");
    controller.createProduct("Keratin shampoo");
    controller.listProduct();

    controller.payment(3000.98);
  }
}
