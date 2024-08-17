package com.example.service;

import com.example.entity.Account;
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

    public Account login(Account account) {
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (dbAdmin == null) {
            //没有用户
            throw new CustomException("账户或密码错误");
        }
        //比较密码
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException("账户或密码错误");
        }
        // 登录成功
        return dbAdmin;

    }

}
