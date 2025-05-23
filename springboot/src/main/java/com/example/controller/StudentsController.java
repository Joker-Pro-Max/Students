package com.example.controller;


import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.Student;
import com.example.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/students")
public class StudentsController {

    @Resource
    StudentService studentService;

    // 新增
    @PostMapping("/add")
    public Result add(@RequestBody Student student) {
        studentService.add(student);
        return Result.success();
    }

    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        studentService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Student student) {
        studentService.updateBy(student);

        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             Student student) {
        PageInfo<Student> pageInfo = studentService.selectPage(pageNum, pageSize, student);

        return Result.success(pageInfo);
    }


}
