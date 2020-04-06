package com.tc.security.distributed.uaa.service;


import com.tc.security.distributed.uaa.dao.UserDao;
import com.tc.security.distributed.uaa.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/4/4 14:29
 * @Description:
 */
@Service
public class SpringDataUserDetailService implements UserDetailsService {
    @Autowired
    UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //将来连接数据库根据账号查询用户信息
        UserDTO userDTO = userDao.getUserByUsername(username);
        if (userDTO == null){
            return null;
        }
        //暂时采用模拟方式
        //登录账号
        System.out.println("username = " + username);
        //暂时采用模拟方式
        String[] strings = null;
        List<String> permissions = userDao.findPermissionsByUserId(userDTO.getId());
        if (!CollectionUtils.isEmpty(permissions)){
            strings = permissions.toArray(new String[permissions.size()]);
        }
        UserDetails userDetails = User.builder().
                username(userDTO.getUsername()).
                password(userDTO.getPassword()).
                authorities(strings).build();
        return userDetails;
    }
}
