package com.tc.security.springmvc.interceptors;

import com.tc.security.springmvc.model.UserDTO;
import org.aopalliance.intercept.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: tianchao
 * @Date: 2020/4/1 19:47
 * @Description:
 */
@Component
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这个方法中校验用户请求的url是否在用户的权限范围内
        Object user = request.getSession().getAttribute(UserDTO.SESSION_USER_KEY);
        String requestURI= request.getRequestURI();
        if (requestURI.contains("/login")||requestURI.contains("/logout")){
            return true;
        }
        if (user == null){
            writeContent(response,"请登录");
        }
        UserDTO userDTO = (UserDTO)user;

        System.err.println(user+" 登录访问 "+requestURI);
        if (userDTO.getAuthorities().contains("p1")&&requestURI.contains("/r/r1")){
            return true;
        }
        if (userDTO.getAuthorities().contains("p2")&&requestURI.contains("/r/r2")){
            return true;
        }
        writeContent(response, userDTO+" 无权访问 "+requestURI);
        return false;
    }

    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(msg);
        printWriter.close();
        //response.resetBuffer();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
