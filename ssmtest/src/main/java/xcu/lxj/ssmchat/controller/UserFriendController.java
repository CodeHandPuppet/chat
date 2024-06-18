package xcu.lxj.ssmchat.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xcu.lxj.ssmchat.pojo.UserFriend;
import xcu.lxj.ssmchat.pojo.UserNotification;
import xcu.lxj.ssmchat.service.UserFriendService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.List;

@Controller
@ResponseBody
public class UserFriendController {

    @Resource
    UserFriendService userFriendServiceImpl;

//  获取
    @GetMapping(path = "/getUserFriend")
    public ResponseBadyData getUserFriend(@RequestHeader("token") String token){

        List<UserFriend> userAllFriends = userFriendServiceImpl.getUserAllFriends(token);

        return ResponseBadyData.success(userAllFriends);
    }
//  添加
    @PostMapping("/addUserFriend")
    public ResponseBadyData addUserFriend(
            @RequestHeader("token") String token,
            @RequestBody UserNotification userNotification
            ){

        userFriendServiceImpl.saveUserFriend(token, userNotification);
        return ResponseBadyData.success(null);
    }
//  更新
    @PostMapping("/updateUserFriend")
    public ResponseBadyData updateUserFriend(
            @RequestHeader("token") String token,
            @RequestBody UserFriend userFriend
    ){

        userFriendServiceImpl.updateUserFriend(token, userFriend);
        return ResponseBadyData.success(null);
    }
//  删除
    @GetMapping("/deleteUserFriend")
    public ResponseBadyData deleteUserFriend(@RequestParam("fid")String fid,@RequestHeader("token") String token){

        userFriendServiceImpl.removeUserFriend(token, fid);

        return ResponseBadyData.success(null);
    }
}
