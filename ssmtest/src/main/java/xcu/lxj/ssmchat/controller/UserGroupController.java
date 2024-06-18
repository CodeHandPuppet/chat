package xcu.lxj.ssmchat.controller;


import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import xcu.lxj.ssmchat.pojo.UserFriend;
import xcu.lxj.ssmchat.pojo.UserGroup;
import xcu.lxj.ssmchat.service.UserGroupService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.List;

@Controller
@ResponseBody
public class UserGroupController {

    @Resource
    UserGroupService userGroupServiceImpl;

    @GetMapping(path = "/getUserGroup")
    public ResponseBadyData getUserFriend(@RequestHeader("token") String token){

        List<UserGroup> userGroups = userGroupServiceImpl.getUserAllGroup(token);

        return ResponseBadyData.success(userGroups);
    }

}
