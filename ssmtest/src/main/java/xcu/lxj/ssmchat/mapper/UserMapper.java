package xcu.lxj.ssmchat.mapper;

import org.apache.ibatis.annotations.Param;
import xcu.lxj.ssmchat.pojo.User;

import java.util.List;

public interface UserMapper {

    int update(User user);

    User selectOne(String uid);

    void insertOne(User user);

    User selectOneByToken(String token);


    User selectOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password);


    List<User> selectByUsername(String username);

    List<User> selectByUsernameLike(String username);

    //  创建表
    int createUserFriendTable(String uid);

    int createUserReceiverMessageTable(String uid);

    int createUserGroupTable(String uid);

    int createUserContactTable(String uid);

    int createUserNotificationTable(String uid);
    //



}
