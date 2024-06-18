package xcu.lxj.ssmchat.controller;


import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xcu.lxj.ssmchat.pojo.Message;
import xcu.lxj.ssmchat.service.GroupMessageService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.List;

@Controller
@ResponseBody
public class GroupMessageController {

    @Resource
    GroupMessageService groupMessageServiceImpl;

    @GetMapping("/getGroupMessage")
    public ResponseBadyData getGroupMessage(@RequestParam("gid") String gid ){

        List<Message> messages = groupMessageServiceImpl.getMessages(gid);
        return ResponseBadyData.success(messages);
    }

    /**
     *
     * @param message
     * @param gid
     * @return
     */
    @PostMapping("/addGroupMessage")
    public ResponseBadyData addGroupMessage(@RequestBody Message message, @RequestParam("gid")String gid){

        groupMessageServiceImpl.saveMessage(gid,message);

        return ResponseBadyData.success(null);
    }



}
