package com.example.looseCoupling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("UserServiceEmail")
public class UserService {
//  NotificationService notificationService;

//  @Autowired // this will do field injection
  public NotificationService notificationService; // make it public for field injection

  public UserService() {
  }

  /*@Autowired
  public UserService(@Qualifier("emailNotificationService") NotificationService notificationService) {
    this.notificationService = notificationService;
  }*/

  @Autowired
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
