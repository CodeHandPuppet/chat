package xcu.lxj.ssmchat.service;


import xcu.lxj.ssmchat.pojo.UserNotification;

import java.util.List;

public interface UserNotificationService {


    List<UserNotification> getUserNotifications(String token);

    List<UserNotification> getUserNotificationsByType(String token,String type);

    int addUserNotification(String receiverId,UserNotification notification);

    int updateUserNotification(String token,UserNotification notification);

    int deleteUserNotification(String token,String id);
}
