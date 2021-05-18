package com.jiang.LoginDemoJwt.service;

import com.jiang.LoginDemoJwt.dao.AdminMapper;
import com.jiang.LoginDemoJwt.pojo.Admin;
import com.jiang.LoginDemoJwt.utils.JWTUtils;
import com.jiang.LoginDemoJwt.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JWTUtils jwtUtils;

    @Value("${jwt-tokenHead}")
    private String tokenHead;

    @Override
    public Admin selectAdminByName(String name) {
        return adminMapper.selectOne(name);
    }

    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {

        String captcha = (String)request.getSession().getAttribute("captcha");
        System.out.println(captcha);
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码错误，请重新输入");
        }

        //得到用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println(userDetails);
        //用户信息为空，且密码不正确 返回错误

        if(null == userDetails || !passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码错误");
        }

        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用,请联系管理员");
        }

        //更新security登录用户对象

        /***
         * 以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        String token = jwtUtils.generateToken(userDetails);

        //将token和tokenHead传入前端
        Map<String, String> map = new HashMap<>();
        map.put("token",token);
        map.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",map);
    }





}
