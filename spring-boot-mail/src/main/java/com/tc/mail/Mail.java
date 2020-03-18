package com.tc.mail;

import lombok.Data;

/**
 * @Auther: tianchao
 * @Date: 2020/3/17 20:03
 * @Description:
 */
@Data
public class Mail {
    private String to;
    private String title;
    private String content;
}
