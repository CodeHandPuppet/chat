package xcu.lxj.ssmchat.service;

import xcu.lxj.ssmchat.pojo.UserFriend;
import xcu.lxj.ssmchat.pojo.UserNotification;

import java.util.List;

public interface UserFriendService {

    List<UserFriend> getUserAllFriends(String token);

    int saveUserFriend(String token, UserNotification userNotification);

    int updateUserFriend(String token,UserFriend friend);

    int removeUserFriend(String token,String fid);
}
