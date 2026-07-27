import looseCoupling.EmailNotificationService;
import looseCoupling.NotificationService;
import looseCoupling.SMSNotificationService;
import tightCoupling.UserService;

public class AppMain {

  public static void main(String[] args) {
    // Example: Tight coupling
    UserService userService = new UserService();
    userService.notifyUser("Zomato Order Placed...!");


    // Example: loose coupling
    NotificationService emailService = new EmailNotificationService();
    NotificationService smsService = new SMSNotificationService();
    looseCoupling.UserService userServiceLoose = new looseCoupling.UserService(smsService);
    userServiceLoose.notifyUser("KFC Order Processed...!");

    
    

  }

}
