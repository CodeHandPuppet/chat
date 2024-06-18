package xcu.lxj.ssmchat.service;


import xcu.lxj.ssmchat.pojo.Message;

import java.util.List;

public interface UserMessageService {

    List<Message> getMessages(String token,String fid);

    void saveMessage(String token, Message message);

    void deleteMessageByFid(String token,String fid);

}


