package com.tc.mapper;

import com.tc.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/3/13 20:49
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    DataSource dataSource;
    @Test
    public void test(){
        System.out.println(dataSource);
        List<User> list = userMapper.list();
        list.stream().forEach(System.out::println);
    }


}