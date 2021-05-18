package com.jiang.LoginDemoJwt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/****
 * 这个类用于登录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginAdmin {

    //用户名
    private String username;

    //密码
    private String password;

    //验证码
    private String code;

}
