package com.tc.mapper;

import com.tc.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: tianchao
 * @Date: 2020/3/13 20:40
 * @Description:
 */
public interface UserMapper {

    @Select("select * from t_user")
    List<User> list();
}
