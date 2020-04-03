package com.tc.security.springmvc.service.impl;

import com.tc.security.springmvc.model.AuthenticationRequest;
import com.tc.security.springmvc.model.UserDTO;
import com.tc.security.springmvc.service.AuthenticationService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: tianchao
 * @Date: 2020/3/31 22:56
 * @Description:
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    /**
     * 用户认证 校验用户名和密码是否合法
     *
     * @param authenticationRequest 用户认证请求,账号和密码
     * @return 认证成功的用户信息
     */
    @Override
    public UserDTO authentication(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest == null
                || StringUtils.isEmpty(authenticationRequest.getUsername())
                || StringUtils.isEmpty(authenticationRequest.getPassword())){
            throw new RuntimeException("用户名或者密码不能为空");
        }

        UserDTO user = getUserDTO(authenticationRequest.getUsername());
        if (user == null){
            throw new RuntimeException("用户不存在");
        }
        if (!user.getPassword().equals(authenticationRequest.getPassword())){
            throw new RuntimeException("密码不存在");
        }
        return user;
    }

    private UserDTO getUserDTO(String username) {
        return userMap.get(username);
    }


    private Map<String,UserDTO> userMap = new HashMap<>();
    {
        Set<String> zhangsanp = new HashSet<>();
        zhangsanp.add("p1");
        Set<String> lisip = new HashSet<>();
        lisip.add("p2");
        userMap.put("zhangsan", new UserDTO("1010","zhangsan","123","张三","133443",zhangsanp));
        userMap.put("lisi", new UserDTO("1011","lisi","456","李四","144553",lisip));

    }
}
