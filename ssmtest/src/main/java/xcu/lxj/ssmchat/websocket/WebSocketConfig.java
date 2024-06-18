package xcu.lxj.ssmchat.websocket;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import xcu.lxj.ssmchat.pojo.User;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableWebSocket
//@ComponentScan("xcu.lxj.ssmchat")
//@Component
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    MyWebSocketHandler myWebSocketHandler;
    @Resource
    MyHandshakeInterceptor myHandshakeInterceptor;


//    @Bean("onlinePeople")
//    public Map<String, WebSocketSession> registyClientMap(){
//        return new HashMap<String, WebSocketSession>();
//    }



    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(myWebSocketHandler,"/websocket")
                .setAllowedOrigins("*")
                .addInterceptors(myHandshakeInterceptor);
    }



}
