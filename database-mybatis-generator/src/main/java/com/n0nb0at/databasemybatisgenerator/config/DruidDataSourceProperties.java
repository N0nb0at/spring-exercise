package com.n0nb0at.databasemybatisgenerator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author guopeng
 * @date 2019-04-15
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidDataSourceProperties {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int maxActive;
    private int minIdle;
    private String validationQuery;
}
