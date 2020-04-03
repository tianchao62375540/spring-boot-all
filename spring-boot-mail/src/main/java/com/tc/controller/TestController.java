package com.tc.controller;

import com.tc.common.ServerResponse;
import com.tc.mail.Mail;
import com.tc.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: tianchao
 * @Date: 2020/3/18 20:15
 * @Description:
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("send")
    public ServerResponse sendMail(@Validated Mail mail, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ServerResponse.error(msg);
        }
        return testService.send(mail);
    }
}
