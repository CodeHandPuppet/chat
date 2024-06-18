package xcu.lxj.ssmchat.controller;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import xcu.lxj.ssmchat.pojo.UserContact;
import xcu.lxj.ssmchat.service.UserContactService;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class ContactController {

    @Resource
    UserContactService userContactServiceImpl;

    @GetMapping("/getContact")
    public ResponseBadyData getContact(@RequestParam("uid") String uid){

        List<UserContact> contact = userContactServiceImpl.getContact(uid);
        return ResponseBadyData.success(contact);
    }

    @PostMapping("/addContact")
    public ResponseBadyData addContact(@RequestHeader("token") String token,@RequestBody UserContact userContact){

        UserContact result = userContactServiceImpl.saveContact(token, userContact);

        return ResponseBadyData.success(result);
    }

}
