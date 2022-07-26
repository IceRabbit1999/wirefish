package com.icerabbit.wirefish.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author iceRabbit
 * @Date 7/26/22 6:27 PM
 **/
@Component
@Configuration
@Data
@ConfigurationProperties
public class ApplicationConfig {

    private Ssh ssh;

    @Data
    public static class Ssh{
        private String prvKeyPath;
    }
}
