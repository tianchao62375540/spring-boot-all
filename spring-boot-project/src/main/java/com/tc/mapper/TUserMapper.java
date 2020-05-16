package com.tc.mapper;

import com.tc.model.TUser;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TUserMapper extends Mapper<TUser> {

    @Select("select * from t_user where username LIKE CONCAT('%',#{username},'%')")
    TUser getUserByUsername(String username);
}