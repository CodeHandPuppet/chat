package xcu.lxj.ssmchat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import xcu.lxj.ssmchat.pojo.GroupMember;
import xcu.lxj.ssmchat.pojo.GroupsInfo;
import xcu.lxj.ssmchat.service.GroupService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.Map;

@Controller
@ResponseBody
public class GroupController {

    @Resource
    GroupService groupServiceImpl;

    @PostMapping("/addGroup")
    public ResponseBadyData addGroup(@RequestBody GroupsInfo group){


        Boolean flag = groupServiceImpl.addGroup(group);

        if(!flag){
            return ResponseBadyData.failure(null);
        }
        return ResponseBadyData.success(null);
    }
//  创建群聊
    @PostMapping("/createGroup")
    public ResponseBadyData createGroup(
            @RequestBody GroupsInfo groupsInfo
            ){

        groupServiceImpl.createGroup(groupsInfo);

        return ResponseBadyData.success(null);
    }
}