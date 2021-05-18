package com.jiang.LoginDemoJwt.pojo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin implements UserDetails, Serializable {
    private String name;
    private String password;

    /**
     * getAuthorities   得到当前角色的权限  返回集合
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    /***
     * getUsername  得到用户名
     * @return
     */
    @Override
    public String getUsername() {
        return name;
    }

    /***
     * isAccountNonExpired  账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isAccountNonLocked  账户是否锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /***
     *  isCredentialsNonExpired   凭证是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /***
     *  isEnabled  是否被禁用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
