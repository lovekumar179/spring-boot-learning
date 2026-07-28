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

    /*
        -> DEPENDENCY INJECTIONS <-
    Constructor Injection - dependency is provided via constructor
    Setter injection - dependency is provided via setter method
    Field Injection - dependency is assigned directly to a field
*/

    // Setter dependency injection
    looseCoupling.UserService userServiceLooseSetter
        = new looseCoupling.UserService(smsService);
    userServiceLooseSetter.setNotificationService(emailService);

    // Field dependency injection
    userServiceLooseSetter.notificationService = smsService;
      
    


  }

}
