package xcu.lxj.ssmchat.controller;


import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xcu.lxj.ssmchat.mapper.UserContactMapper;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.pojo.UserContact;
import xcu.lxj.ssmchat.service.UserService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class UserController {


    @Resource
    UserService userServiceImpl;

    //获取单个用户个人信息
    @GetMapping("/user")
    public ResponseBadyData getUser(
            @RequestParam(value = "uid",required = false) String uid ,
            @RequestHeader(value = "token",required = false) String token
    ) {

        User user = null;

        if(uid == null){
            user=userServiceImpl.findUserByToken(token);
        }
        else{
            user = userServiceImpl.findUser(uid);
        }
        if(user == null){
            return ResponseBadyData.failure((null));
        }
        return ResponseBadyData.success(user);

    }

    //更改用户个人信息
    @PutMapping("/userUpdate")
    public ResponseBadyData updateUser(@RequestBody User user) {

        Boolean b = userServiceImpl.doUpdateUser(user);
        if (b == true) {
            return ResponseBadyData.success(null);
        }
        else
            return ResponseBadyData.failure(null);
    }

    //登录
    @PostMapping("/login")
    public ResponseBadyData login(@RequestBody User user, HttpServletRequest request) {

        User user1= userServiceImpl.doLogin(user.getUsername(), user.getPassword());




        if (user1 != null) {
            return ResponseBadyData.success(user1.getToken());
        }
        else
            return ResponseBadyData.failure(null);
    }

    //退出登录
    @GetMapping("/logout")
    public ResponseBadyData logout() {


        return null;
    }

    // 注册用户
    @PostMapping("/signin")
    public ResponseBadyData signin(@RequestBody User user) {
//        try {
            boolean b = userServiceImpl.addUser(user);
//        }catch (Exception e){
//            throw e;
//        }
//       return ResponseBadyData.success(null);

        if (b == true) {
            return ResponseBadyData.success(null);
        }
        else
            return ResponseBadyData.failure(null);
    }

//  通过用户名搜索用户
    @GetMapping("/getUserByUsername")
    public ResponseBadyData getUserByUsername(@RequestParam("username") String username){

        List<User> users = userServiceImpl.getUserByUsername(username);
        return ResponseBadyData.success(users);
    }

//  通过用户名模糊匹配用户
    @GetMapping("/getLikeUser")
    public ResponseBadyData getLikeUser(@RequestParam("username") String username){

        return ResponseBadyData.success(userServiceImpl.getUserByUsernameLike(username));

    }

}
