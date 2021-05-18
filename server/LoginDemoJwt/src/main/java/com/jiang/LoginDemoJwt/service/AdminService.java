package com.jiang.LoginDemoJwt.service;

import com.jiang.LoginDemoJwt.pojo.Admin;
import com.jiang.LoginDemoJwt.utils.RespBean;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    Admin selectAdminByName(String name);

    RespBean login(String username, String password, String code, HttpServletRequest request);
}
