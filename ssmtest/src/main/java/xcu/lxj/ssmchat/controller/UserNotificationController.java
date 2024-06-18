package xcu.lxj.ssmchat.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xcu.lxj.ssmchat.pojo.UserNotification;
import xcu.lxj.ssmchat.service.UserNotificationService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class UserNotificationController {

    @Resource
    UserNotificationService userNotificationServiceImpl;

    @GetMapping(value ="/getUserNotification")
    public ResponseBadyData getUserNotification(@RequestHeader("token") String token){

        List<UserNotification> userNotifications = userNotificationServiceImpl.getUserNotifications(token);
        return ResponseBadyData.success(userNotifications);
    }
//    通过类型获得 通知
    @GetMapping(value ="/getUserNotificationByType")
    public ResponseBadyData getUserNotificationByType(
            @RequestHeader("token") String token,
            @RequestParam("type") String type){
        List<UserNotification> userNotifications = userNotificationServiceImpl.getUserNotificationsByType(token, type);

        return ResponseBadyData.success(userNotifications);
    }
//  添加
    @PostMapping(value = "/addUserNotification")
    public ResponseBadyData addUserNotification(@RequestBody Map<String,Object> map){

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String,String> mappp= (HashMap<String, String>) map.get("userNotification");
        UserNotification userNotification= objectMapper.convertValue(mappp, UserNotification.class);
        String receiverId=map.get("receiverId").toString();
        userNotificationServiceImpl.addUserNotification(receiverId, userNotification);
        return ResponseBadyData.success(null);

    }
//  更新
    @PostMapping("/updateNotification")
    public ResponseBadyData updateNotification(
            @RequestHeader("token") String token,
            @RequestBody UserNotification userNotification){

        userNotificationServiceImpl.updateUserNotification(token,userNotification);


        return ResponseBadyData.success(null);
    }
//  删除
    @GetMapping("/deleteNotification")
    public ResponseBadyData deleteNotification(
            @RequestParam("id")String id,
            @RequestHeader("token") String token){

        userNotificationServiceImpl.deleteUserNotification(token,id);


        return ResponseBadyData.success(null);
    }
}
