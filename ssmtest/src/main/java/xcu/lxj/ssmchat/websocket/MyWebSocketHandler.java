package xcu.lxj.ssmchat.websocket;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import xcu.lxj.ssmchat.pojo.Message;
import xcu.lxj.ssmchat.pojo.SocketMessage;
import xcu.lxj.ssmchat.service.SocketMessageService;

import java.util.Map;


@Service
public class MyWebSocketHandler extends TextWebSocketHandler {





    @Resource
    Map<String, WebSocketSession> onlinePeople;
    @Resource
    SocketMessageService socketMessageServiceImpl;
//  接收到消息做出回应
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


        ObjectMapper objectMapper = new ObjectMapper();
        //打印过来传递的  message
        System.out.println("\n================================\n");
        System.out.println("user :" + session.getAttributes().get("user"));
        System.out.println("onlinePeople : "+onlinePeople );
        System.out.println("message = "+message.getPayload());
        System.out.println("\n================================\n");
        SocketMessage socketMessage = objectMapper.readValue(message.getPayload(),new TypeReference<SocketMessage<Object>>() {} );

//  1. 获取 socketMessage 的内容
        String type = socketMessage.getType();
//  2. 通过内容确定做什么
        if(type.equals("close")){
//      如果为  close  关闭 对话
            session.close();
            return;
        }
        if(type.equals("chat")){
//      如果为 chat 认定为聊天 判断聊天的是人还是群
            String receiverType = socketMessage.getReceiverType();
            String receiverId = socketMessage.getReceiverId();
            String content = socketMessage.getContent();
            String sendId = session.getAttributes().get("userId").toString();

            if(receiverType.equals("user")){
                socketMessageServiceImpl.userMessageHandle(socketMessage);
            }
            else if((receiverType.equals("group"))){
                socketMessageServiceImpl.GroupMessageHandle(socketMessage);

            }

        }


    }



// 开启连接
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        String userId = session.getAttributes().get("userId").toString();
        onlinePeople.put(userId,session);

    }
//  关闭连接
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        onlinePeople.remove(session.getId());
    }
}
