package xcu.lxj.ssmchat.service.impl;


import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.service.UserService;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User findUser(String uid) {
        return userMapper.selectOne(uid);
    }

    @Override
    public User doLogin(String username, String password) {

        User user = userMapper.selectOneByUsernameAndPassword(username, password);


        return user;
    }

    @Override
    public Boolean doUpdateUser(User user) {

        int update = userMapper.update(user);
        if(update == 0){
            return false;
        }
        return true;
    }



    @Override
    public boolean addUser(User user){

        userMapper.insertOne(user);
        String uid=user.getUid();
//      当用户注册好的时候 创建这个user表
        userMapper.createUserFriendTable(uid);
        userMapper.createUserReceiverMessageTable(uid);
        userMapper.createUserGroupTable(uid);
        userMapper.createUserContactTable(uid);
        userMapper.createUserNotificationTable(uid);

        return true;


    }

    @Override
    public User findUserByToken(String token) {

        return userMapper.selectOneByToken(token);

    }

    @Override
    public List<User> getUserByUsername(String username) {


        return userMapper.selectByUsername(username);
    }

    @Override
    public List<User> getUserByUsernameLike(String username) {
        return userMapper.selectByUsernameLike(username);
    }
}
