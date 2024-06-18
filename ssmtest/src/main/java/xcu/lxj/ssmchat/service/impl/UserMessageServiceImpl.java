package xcu.lxj.ssmchat.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.mapper.UserMessageMapper;
import xcu.lxj.ssmchat.pojo.Message;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.service.UserMessageService;

import java.util.*;

@Transactional
@Service
public class UserMessageServiceImpl implements UserMessageService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserMessageMapper userMessageMapper;



    @Override
    public List<Message> getMessages(String token,String fid) {

//  获得全部消息
//  1. 通过token 获得用户消息
        User user = userMapper.selectOneByToken(token);
//  2. 通过uid获取message
        String uid = user.getUid();
//  3. 获取我接收关于他的消息
        List<Message> receiverMessage = userMessageMapper.selectOne(uid, fid);
//  4，获取他接收（我发送）的消息
        List<Message> sendMessage = userMessageMapper.selectOne(fid,uid);
        List<Message> messages =new ArrayList<Message>();
        messages.addAll(receiverMessage);
        messages.addAll(sendMessage);
        Collections.sort(messages, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return o1.getSendTime().compareTo(o2.getSendTime());
            }
        });
        return messages;

    }



    @Override
    public void saveMessage(String token, Message message) {
//   保存消息
//   1. 通过token获得用户信息 user
        User user = userMapper.selectOneByToken(token);
//   2. 处理message（message 里面的 uid 是接收人的 uid ） 但发送人是我们
        String sendId = user.getUid();
        String receiverId = message.getUid();
//   3. 把message 里面的接收人id 改为需要存储的 发送人id
        message.setUid(sendId);

        userMessageMapper.insertOne(receiverId, message);
    }



    @Override
    public void deleteMessageByFid(String token, String fid) {

//  通过 fid 删除消息
//  1. 通过token获得用户信息 user
        User user = userMapper.selectOneByToken(token);
//  2. 保存
        String uid = user.getUid();
        userMessageMapper.deleteByFid(uid,fid);


    }



}
