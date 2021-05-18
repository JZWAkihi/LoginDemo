package com.jiang.LoginDemoJwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiang.LoginDemoJwt.dao")
public class LoginDemoJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginDemoJwtApplication.class, args);
    }

}
