package xcu.lxj.ssmchat.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import xcu.lxj.ssmchat.mapper.*;
import xcu.lxj.ssmchat.pojo.*;
import xcu.lxj.ssmchat.service.SocketMessageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Transactional(isolation = READ_UNCOMMITTED)
@Service
public class SocketMessageServiceImpl implements SocketMessageService {


    @Resource
    Map<String, WebSocketSession> onlinePeople;
    @Resource
    UserContactMapper userContactMapper;
    @Resource
    UserMessageMapper userMessageMapper;
    @Resource
    GroupMemberMapper groupMemberMapper;
    @Resource
    GroupMessageMapper groupMessageMapper;
    @Override
    public void userMessageHandle(SocketMessage socketMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String receiverId = socketMessage.getReceiverId();
        String content = socketMessage.getContent();
        String senderId = socketMessage.getSenderId();
        User user = (User) onlinePeople.get(senderId).getAttributes().get("user");
//      更新检查 receiver 的 userContact 让 receiver 一定存在
        UserContact flagUserContact = userContactMapper.selectOneByFid(receiverId, senderId);
//      如果没有这个常联系的人则添加这个长联系人
        if(flagUserContact == null){
            UserContact userContact=new UserContact();
            userContact.setFid(senderId);
            userContact.setType("user");
//              给 receiver 插入 senderId 的常用
            userContactMapper.insertOne(receiverId,userContact);
            //判断在线不 在线给他的常用联系人更新了
            WebSocketSession webSocketSession = onlinePeople.get(receiverId);
            if(webSocketSession != null){
                SocketMessage<UserContact> userContactSocketMessage = new SocketMessage<>();
                userContactSocketMessage.setReceiverId(receiverId);
                userContactSocketMessage.setType("userContact");
                userContactSocketMessage.setReceiverType("user");
//              查询这个data 放入 userContactSocketMessage
                UserContact socket_date = userContactMapper.selectOneByFid(receiverId,senderId);
                userContactSocketMessage.setData(socket_date);
//              发送给前端
                webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(userContactSocketMessage)));
            }
        }
//      不在线的情况 而且 必须要在线必须要做的 是将数据写入数据库
        Message message=new Message();
        message.setUid(senderId);
        message.setContent(content);
//      放入user
        userMessageMapper.insertOne(receiverId,message);
//      在线
        WebSocketSession webSocketSession = onlinePeople.get(receiverId);
        if(webSocketSession != null){

//          将user的信息封装到里面
            message.setUser(user);
            socketMessage.setData(message);
//          给在线的他发送消息
            webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(socketMessage)));

        }

    }

    @Override
    public void GroupMessageHandle(SocketMessage socketMessage) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String gid = socketMessage.getReceiverId();
        String content = socketMessage.getContent();
        String senderId = socketMessage.getSenderId();
//      发送者 个人信息
        User user = (User) onlinePeople.get(senderId).getAttributes().get("user");
        //      将 message 保存到 group_message中


        Message message=new Message();
        message.setUid(senderId);
        message.setContent(content);
        groupMessageMapper.insertOne(gid,message);
        List<GroupMember> groupMembers = groupMemberMapper.selectAll(gid);
        for (GroupMember groupMember : groupMembers){
//          获取接收者id
            String receiverId = groupMember.getUid();
            if(receiverId.equals(senderId)) continue;
//          群成员在线不在线都需要创建一个 userContact
            UserContact flagContact = userContactMapper.selectOneByGid(receiverId, gid);
//          没有就插入 userContact
            if(flagContact == null){
                UserContact userContact=new UserContact();
                userContact.setGid(gid);
                userContact.setType("group");
                userContactMapper.insertOne(receiverId,userContact);
                WebSocketSession webSocketSession = onlinePeople.get(receiverId);
                if(webSocketSession != null){
                    SocketMessage<UserContact> userContactSocketMessage = new SocketMessage<>();
                    userContactSocketMessage.setReceiverId(receiverId);
                    userContactSocketMessage.setType("userContact");
                    userContactSocketMessage.setReceiverType("user");
//              查询这个data 放入 userContactSocketMessage
                    UserContact socket_date = userContactMapper.selectOneByGid(receiverId,gid);
                    userContactSocketMessage.setData(socket_date);
//              发送给前端
                    webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(userContactSocketMessage)));
                }

            }
//          判断接收人是不是在线 在线向他发送消息 不在线则不处理
            WebSocketSession webSocketSession = onlinePeople.get(receiverId);
            if(webSocketSession != null){
                message.setUser(user);

                socketMessage.setData(message);
                webSocketSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(socketMessage)));
            }
        }
//        遍历每一个 在线的群成员
    }
}
