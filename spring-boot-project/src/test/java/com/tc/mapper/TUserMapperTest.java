package com.tc.mapper;

import com.tc.model.TUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/4/7 22:19
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TUserMapperTest {
    @Autowired
    TUserMapper tUserMapper;
    @Test
    public void test(){
        List<TUser> tUsers = tUserMapper.selectAll();
        tUsers.forEach(System.out::println);
    }
}