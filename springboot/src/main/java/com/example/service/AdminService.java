package com.example.service;

import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Autowired
    private RestTemplateAutoConfiguration restTemplateAutoConfiguration;

    public Admin login(Admin admin) {
        Admin dbadmin = adminMapper.selectByUsername(admin.getUsername());
        if (dbadmin == null) {
            //没有用户
            throw new CustomException("账户或密码错误");
        }
        //比较密码
        if (!admin.getPassword().equals(dbadmin.getPassword())) {
            throw new CustomException("账户或密码错误");
        }
        // 登录成功
        return dbadmin;

    }

}
