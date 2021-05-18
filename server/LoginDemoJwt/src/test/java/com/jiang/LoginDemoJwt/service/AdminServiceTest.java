package com.jiang.LoginDemoJwt.service;

import com.jiang.LoginDemoJwt.dao.AdminMapper;
import com.jiang.LoginDemoJwt.utils.JWTUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class AdminServiceTest {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void Test1(){
        System.out.println(passwordEncoder.matches("123", "$2a$10$ogvUqZZAxrBwrmVI/e7.SuFYyx8my8d.9zJ6bs9lPKWvbD9eefyCe"));
    }


}
