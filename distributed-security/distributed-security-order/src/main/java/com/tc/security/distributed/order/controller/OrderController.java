package com.tc.security.distributed.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianchao
 * @Date: 2020/4/6 23:42
 * @Description:
 */
@RestController
public class OrderController {

    @GetMapping("/r1")
    @PreAuthorize("hasAnyAuthority('p4')")
    public String r1(){
        return "访问资源1";
    }
    @GetMapping("/r2")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r2(){
        return "访问资源2";
    }
}
