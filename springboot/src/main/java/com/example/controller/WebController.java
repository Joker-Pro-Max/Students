package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.service.AdminService;
import com.example.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebController {
    @Resource
    private StudentService studentService;


    @Resource
    private AdminService adminService;

    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    /**
     * 默认请求接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account dbAccount;
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            dbAccount = adminService.login(account);

        } else if (RoleEnum.STUDENT.name().equals(account.getRole())) {
            dbAccount = studentService.login(account);
        } else {
            return Result.error("角色错误");
        }

        return Result.success(dbAccount);

    }

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getPassword()) || ObjectUtil.isEmpty(account.getUsername())) {
            return Result.error("账号或密码不存在");
        }
        studentService.register(account);
        return Result.success();

    }


}
