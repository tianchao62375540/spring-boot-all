package com.tc.web;

import com.tc.bean.User;
import com.tc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/3/13 21:22
 * @Description:
 */
@RestController
public class UserController {

    @Autowired
    UserMapper userMapper;
    @RequestMapping("/user/list")
    public List<User> list(){
        return userMapper.list();
    }
}
