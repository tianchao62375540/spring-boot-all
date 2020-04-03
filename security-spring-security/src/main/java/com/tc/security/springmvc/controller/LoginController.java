package com.tc.security.springmvc.controller;

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

    @RequestMapping(value = "login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        return "登录成功";
    }
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    public String r2(HttpSession session){
        return " 访问资源r2...";
    }
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session){
        return " 访问资源r1...";
    }
    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "退出成功";
    }
}
