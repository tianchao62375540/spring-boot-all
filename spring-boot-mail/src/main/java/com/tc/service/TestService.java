package com.tc.service;

import com.tc.common.ServerResponse;
import com.tc.mail.Mail;

/**
 * @Auther: tianchao
 * @Date: 2020/3/18 20:31
 * @Description:
 */
public interface TestService {
    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
