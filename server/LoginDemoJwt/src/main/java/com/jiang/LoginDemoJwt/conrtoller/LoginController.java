package com.jiang.LoginDemoJwt.conrtoller;

import com.jiang.LoginDemoJwt.pojo.Admin;
import com.jiang.LoginDemoJwt.pojo.LoginAdmin;
import com.jiang.LoginDemoJwt.service.AdminServiceImpl;
import com.jiang.LoginDemoJwt.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    private AdminServiceImpl adminService;


    @PostMapping("/login")
    public RespBean login(@RequestBody LoginAdmin loginAdmin, HttpServletRequest request){
        return adminService.login(loginAdmin.getUsername(),loginAdmin.getPassword(),loginAdmin.getCode(),request);
    }

    @GetMapping("/info")
    public Admin getAdminInfo(Principal principal){
        if (principal == null){
            return null;
        }
        System.out.println(principal.getName());
        String username = principal.getName();
        Admin admin = adminService.selectAdminByName(username);
        admin.setPassword("********");
        return admin;
    }


}
