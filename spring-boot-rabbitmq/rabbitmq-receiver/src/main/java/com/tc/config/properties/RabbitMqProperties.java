package com.tc.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Auther: tianchao
 * @Date: 2020/1/23 14:57
 * @Description:
 */
@ConfigurationProperties(prefix = "tc.rabbitmq")
@Data
public class RabbitMqProperties {
    /**
     *
     */
    private String hostname;

    private int port;

    private String username;

    private String password;

    private String virtualHost;
}
