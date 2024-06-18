package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.UserFriendMapper;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.mapper.UserNotificationMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserFriend;
import xcu.lxj.ssmchat.pojo.UserNotification;
import xcu.lxj.ssmchat.service.UserFriendService;

import java.util.List;


@Transactional
@Service
public class UserFriendServiceImpl implements UserFriendService {
    @Resource
    UserFriendMapper userFriendMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    UserNotificationMapper userNotificationMapper;

    @Override
    public List<UserFriend> getUserAllFriends(String token) {

        User user = userMapper.selectOneByToken(token);
        String uid = user.getUid();

        List<UserFriend> userFriends = userFriendMapper.selectFriends(uid);

        return userFriends;
    }

    @Override
    public int saveUserFriend(String token, UserNotification userNotification) {

        User user = userMapper.selectOneByToken(token);
        String uid = user.getUid();
//      好友是双向的
        String fid = userNotification.getUid();
        User friend=userNotification.getUser();

        userFriendMapper.insertOneByFid(uid,fid);
        userFriendMapper.insertOneByFid(fid,uid);
//      双向添加好友
//      添加成功后更新通知
        userNotification.setAgree(true);
        userNotification.setIsFinish(true);
//
        userNotificationMapper.update(uid,userNotification);
        return 0;
    }

    @Override
    public int updateUserFriend(String token, UserFriend friend) {
        User user = userMapper.selectOneByToken(token);
        String uid = user.getUid();

        userFriendMapper.updateOne(uid,friend);
        return 0;
    }

    @Override
    public int removeUserFriend(String token, String fid) {

        User user = userMapper.selectOneByToken(token);
        String uid = user.getUid();

        userFriendMapper.deleteOneById(uid,fid);
        userFriendMapper.deleteOneById(fid,uid);

        return 0;
    }
}
