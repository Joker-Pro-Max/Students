package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.exception.CustomException;
import com.example.mapper.CourseMapper;
import com.example.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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


    public void register(Account account) {
        Student student = new Student();
        student.setUsername(account.getUsername());
        student.setPassword(account.getPassword());

        this.add(student);


    }

    //新增
    public void add(Student student) {
        Student dbStudent = studentMapper.selectByUsername(student.getUsername());
        if (dbStudent != null) {  // 用户已经存在
            throw new CustomException("账号已存在");
        }
        if (ObjectUtil.isEmpty(student.getName())) {
            student.setName(student.getUsername());
        }
        student.setRole(RoleEnum.STUDENT.name());
        // 写进数据库
        studentMapper.insert(student);
    }

    //删除
    public void deleteById(Integer id) {
        studentMapper.deleteById(id);
    }

    // 修改
    public void updateBy(Student student) {
        studentMapper.updateById(student);
    }

    // 分页查询
    public PageInfo<Student> selectPage(Integer pageNum, Integer pageSize, Student student) {

        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentsList = studentMapper.selectAll(student);
        return PageInfo.of(studentsList);
    }
}
