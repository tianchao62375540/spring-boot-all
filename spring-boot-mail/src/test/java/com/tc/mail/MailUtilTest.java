package com.tc.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Auther: tianchao
 * @Date: 2020/3/17 20:05
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailUtilTest {


    @Autowired
    MailUtil mailUtil;

    @Test
    public void testSend(){
        Mail mail = new Mail();
        //mail.setTo("tianchao623755@163.com");
        mail.setTo("tianchao623755@163.com");
        mail.setTitle("邮件主题主题");
        mail.setContent("您好啊我只是想测试啊 ");
        mailUtil.send(mail);
    }




}