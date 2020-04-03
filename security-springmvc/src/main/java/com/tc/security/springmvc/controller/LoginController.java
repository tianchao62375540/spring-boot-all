package com.tc.security.springmvc.controller;

import com.sun.org.apache.regexp.internal.REUtil;
import com.tc.security.springmvc.model.AuthenticationRequest;
import com.tc.security.springmvc.model.UserDTO;
import com.tc.security.springmvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 23:05
 * @Description:
 */
@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(AuthenticationRequest authenticationRequest, HttpSession httpSession){
        UserDTO userDTO = authenticationService.authentication(authenticationRequest);
        httpSession.setAttribute(UserDTO.SESSION_USER_KEY, userDTO);
        return userDTO.getFullName() + "登录成功";
    }
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session){
        Object user = session.getAttribute(UserDTO.SESSION_USER_KEY);
        if (user == null){
            return "匿名访问";
        }
        System.err.println(session.getId());
        return ((UserDTO)user).getFullName()+" 访问资源r1...";
    }
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session){
        Object user = session.getAttribute(UserDTO.SESSION_USER_KEY);
        if (user == null){
            return "匿名访问";
        }
        System.err.println(session.getId());
        return ((UserDTO)user).getFullName()+" 访问资源r2...";
    }
    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "退出成功";
    }
}
