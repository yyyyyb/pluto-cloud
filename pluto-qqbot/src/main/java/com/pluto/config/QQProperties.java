package com.pluto.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = QQProperties.PREFIX)
public class QQProperties {
    public static final String PREFIX = "pluto.qqbot";

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否重新登录
     */
    private Boolean reLogin;

    /**
     * 设备协议
     */
    private String protocol;
}
