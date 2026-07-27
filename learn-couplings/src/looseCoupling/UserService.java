package looseCoupling;

public class UserService {
  NotificationService notificationService;

  public UserService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  public void notifyUser(String message){
    notificationService.send("Notification hello");
  }

}
