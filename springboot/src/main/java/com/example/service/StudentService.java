package com.example.service;

import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;

import javax.annotation.Resource;

public class StudentService {
    @Resource
    private StudentMapper studentMapper;


    public Account login(Account account) {
        Account dbAdmin = studentMapper.selectByUsername(account.getUsername());
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
