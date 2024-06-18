package xcu.lxj.ssmchat.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xcu.lxj.ssmchat.pojo.Message;
import xcu.lxj.ssmchat.service.UserMessageService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.List;

@Controller
@ResponseBody
public class UserMessageController {


    @Resource
    UserMessageService userMessageServiceImpl;

    @GetMapping("/getUserMessage")
    public ResponseBadyData getUserMessage(@RequestHeader("token") String token, @RequestParam("fid") String fid){

        List<Message> messages = userMessageServiceImpl.getMessages(token, fid);

        return ResponseBadyData.success(messages);
    }
    @PostMapping("/addUserMessage")
    public ResponseBadyData addUserMessage(@RequestHeader("token") String token,@RequestBody Message message){

        userMessageServiceImpl.saveMessage(token, message);

        return ResponseBadyData.success(null);
    }

}
