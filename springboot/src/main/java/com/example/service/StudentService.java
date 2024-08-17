package com.example.service;

import com.example.entity.Account;
import com.example.exception.CustomException;
import com.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService {
    @Resource
    private StudentMapper studentMapper;


    public Account login(Account account) {
        Account dbStudent = studentMapper.selectByUsername(account.getUsername());
        if (dbStudent == null) {
            //没有用户
            throw new CustomException("账户或密码错误");
        }
        //比较密码
        if (!account.getPassword().equals(dbStudent.getPassword())) {
            throw new CustomException("账户或密码错误");
        }
        // 登录成功
        return dbStudent;

    }


}
