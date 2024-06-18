package xcu.lxj.ssmchat.service;

import xcu.lxj.ssmchat.pojo.Message;

import java.util.List;

public interface GroupMessageService {

    List<Message> getMessages(String gid);

    void saveMessage(String gid, Message message);

    void deleteMessageByUid(String gid,String uid);
}
