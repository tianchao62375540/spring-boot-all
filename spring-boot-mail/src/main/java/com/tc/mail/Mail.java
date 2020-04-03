package com.tc.mail;

import com.tc.pojo.CustomerMsg;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.reflect.Method;

/**
 * @Auther: tianchao
 * @Date: 2020/3/17 20:03
 * @Description:
 */
@Data
public class Mail implements CustomerMsg {
    @Pattern(regexp = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$", message = "邮箱格式不正确")
    private String to;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "正文不能为空")
    private String content;

    private String msgId;// 消息id

    public static void main(String[] args) throws NoSuchMethodException {
        Method getTitle = Mail.class.getMethod("getTitle", null);
        Method getTitle1 = Mail.class.getMethod("getTitle", null);
        System.out.println(getTitle.toGenericString());
        System.out.println(getTitle1.toGenericString());
    }
}
