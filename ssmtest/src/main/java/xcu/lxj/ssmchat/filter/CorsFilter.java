package xcu.lxj.ssmchat.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter(value = "/*")
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain Chain) throws IOException, ServletException {


        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String origin = request.getHeader("Origin");
        System.out.println(origin);
//        response.setHeader("Access-Control-Allow-Origin", "*");//指定授权访问的域，*允许所有url访问
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "*");//授权请求的方法
        response.setHeader("Access-Control-Max-Age", "3600");//60 min
//        response.setHeader("Access-Control-Allow-Headers", "*");//响应头部
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, token");
        response.setHeader("Access-Control-Allow-Credentials", "true");//允许客户端携带验证信息
        response.setContentType("application/json");

        Chain.doFilter(request, response);//通过过滤器后，继续执行原本请求


    }

    @Override
    public void destroy() {

    }
}