package com.tc.security.distributed.uaa.dao;


import com.tc.security.distributed.uaa.model.PermissionDTO;
import com.tc.security.distributed.uaa.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: tianchao
 * @Date: 2020/4/4 18:26
 * @Description:
 */
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public UserDTO getUserByUsername(String username){
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        List<UserDTO> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDTO.class));
        if (list == null || list.size()!=1){
            return null;
        }
        return list.get(0);
    }

    public List<String> findPermissionsByUserId(Long userId){
        String sql = "select * from t_permission where id in(\n" +
                "\tSELECT \n" +
                "\t\tpermission_id \n" +
                "\tfrom  \n" +
                "\t\tt_role_permission \n" +
                "\twhere role_id in(\n" +
                "\t\tSELECT role_id FROM t_user_role where user_id = ?\n" +
                "\t)\n" +
                ")";
        List<PermissionDTO> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDTO.class));
        return list.stream().map(PermissionDTO::getCode).collect(Collectors.toList());

    }
}
