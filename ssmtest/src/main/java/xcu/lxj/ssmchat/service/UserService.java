package xcu.lxj.ssmchat.service;

import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.pojo.User;

import java.util.List;


public interface UserService {

    User findUser(String uid);

    User doLogin(String username ,String password);

    Boolean doUpdateUser(User user);

    boolean addUser(User user);

    User findUserByToken(String token);

    List<User> getUserByUsername(String username);

    List<User> getUserByUsernameLike(String username);

}
