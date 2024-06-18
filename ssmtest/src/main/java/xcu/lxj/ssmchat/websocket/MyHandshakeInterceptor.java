package xcu.lxj.ssmchat.websocket;

import jakarta.annotation.Resource;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.pojo.User;

import java.util.Map;

@Component
public class MyHandshakeInterceptor implements HandshakeInterceptor {


    @Resource
    UserMapper userMapper;





    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {


//      截取发送人的 userId 用于存储
        String[] split = request.getURI().getQuery().split("=");
        System.out.println(split[0]);
        String userIdName = split[0];
        String userId = split[1];
//      userId
        attributes.put(userIdName,userId);
        User user = userMapper.selectOne(userId);
//      user
        attributes.put("user",user);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {

    }
}
