package com.jiang.LoginDemoJwt.dao;


import com.jiang.LoginDemoJwt.pojo.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    Admin selectOne(@Param("name") String name);

}
