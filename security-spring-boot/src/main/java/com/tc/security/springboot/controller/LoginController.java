package com.tc.security.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        return getUsername()+" 登录成功";
    }
    @GetMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAuthority('p3')")
    public String r2(HttpSession session){
        return getUsername()+" 访问资源r2...";
    }
    @GetMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    public String r1(HttpSession session){
        return getUsername()+" 访问资源r1...";
    }
    @GetMapping(value = "/logout",produces = {"text/plain;charset=UTF-8"})
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "退出成功";
    }
    //
    private String getUsername(){
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null){
            username = "匿名用户";
        }else{
            if (principal instanceof UserDetails){
                username = ((UserDetails) principal).getUsername();
            }else{
                username = principal.toString();
            }
        }
        return username;
    }
}
