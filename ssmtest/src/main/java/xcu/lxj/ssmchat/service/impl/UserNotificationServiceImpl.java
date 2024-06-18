package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.mapper.UserNotificationMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserNotification;
import xcu.lxj.ssmchat.service.UserNotificationService;

import java.util.List;

@Transactional
@Service
public class UserNotificationServiceImpl implements UserNotificationService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserNotificationMapper userNotificationMapper;

    @Override
    public List<UserNotification> getUserNotifications(String token) {

        User user = userMapper.selectOneByToken(token);
        String uid=user.getUid();
        List<UserNotification> userNotifications = userNotificationMapper.selectAll(uid);
        return userNotifications;
    }

    @Override
    public List<UserNotification> getUserNotificationsByType(String token, String type) {
        User user = userMapper.selectOneByToken(token);
        String uid=user.getUid();

        List<UserNotification> userNotifications = userNotificationMapper.selectByType(uid,type);
        return userNotifications;
    }

    @Override
    public int addUserNotification(String receiverId, UserNotification notification) {
        try {
            userNotificationMapper.insertOne(receiverId,notification);
        }catch (Exception e){

        }
        return 0;
    }


    @Override
    public int updateUserNotification( String token,UserNotification notification) {

        User user = userMapper.selectOneByToken(token);
        String uid=user.getUid();
//        if(uid != null)
        userNotificationMapper.update(uid,notification);

        return 0;
    }

    @Override
    public int deleteUserNotification(String token, String id) {

        User user = userMapper.selectOneByToken(token);
        String uid=user.getUid();

        userNotificationMapper.deleteById(uid,id);

        return 0;
    }
}
