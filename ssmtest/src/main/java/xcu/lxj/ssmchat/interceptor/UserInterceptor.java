package xcu.lxj.ssmchat.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.WebSocketSession;
import xcu.lxj.ssmchat.mapper.UserMapper;
import xcu.lxj.ssmchat.pojo.User;
import xcu.lxj.ssmchat.utils.ResponseBadyData;

import java.util.Map;

public class UserInterceptor implements HandlerInterceptor {


    @Resource
    UserMapper userMapper;
    @Resource
    Map<String , WebSocketSession> onlinePeople;


    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response, Object handler
    ) throws Exception {

        System.out.println("\n--------------------------------\n");
        System.out.println("UserInterceptor - preHandle");
        System.out.println(request.getServletPath());
        System.out.println("\n--------------------------------\n");
//      如果未ws则直接放行
        if(request.getHeader("Upgrade") != null){
            return true;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        //  用户登录拥有 token 请求头里面 且能通过token获取用户信息
        if (request.getHeader("token") == null) {

            //        如果未登录 ，则只能访问 /login
            if (request.getServletPath().equals("/login") || request.getServletPath().equals("/signin")) {
                return true;
            } else {
                String json = objectMapper.writeValueAsString(ResponseBadyData.failure("1用户信息不存在或者已过期"));
                response.getWriter().print(json);
                return false;
            }
        }
        else {
            String token = request.getHeader("token");
            //不在会话中 验证有无此人 有则放行且记录他的在线信息
            User user = userMapper.selectOneByToken(token);
            if (user != null) {
                return true;
            }
            else {
                //没有 返回登录失败
                String json = objectMapper.writeValueAsString(ResponseBadyData.failure("2用户信息不存在或者已过期"));
                response.getWriter().print(json);
                return false;
            }

        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("\n--------------------------------\n");
        System.out.println("UserInterceptor - afterCompletion");
        System.out.println("onlinePeople.size : " + onlinePeople.size());
        System.out.println("\n--------------------------------\n");

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
