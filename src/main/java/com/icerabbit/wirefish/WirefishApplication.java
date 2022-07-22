package com.icerabbit.wirefish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author iceRabbit
 */
@SpringBootApplication
@ServletComponentScan
public class WirefishApplication {

    public static void main(String[] args) {
        SpringApplication.run(WirefishApplication.class, args);
    }

}
