package com.example.looseCoupling;

public class UserService {
//  NotificationService notificationService;
  public NotificationService notificationService; // make it public for field injection

  public UserService() {
  }

  public UserService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  public void notifyUser(String message){
    notificationService.send("Notification hello");
  }

  public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  

}
