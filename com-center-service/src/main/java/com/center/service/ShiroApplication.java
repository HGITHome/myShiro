package com.center.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = {"com.center.dao","com.center.service","com.center.common.exception"})
@MapperScan(basePackages = "com.center.dao.mapper")
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class,args);
    }
}
