package com.example.controller;

import com.example.common.Result;
import com.example.entity.StudentCourse;
import com.example.service.StudentCourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {
    @Resource
    private StudentCourseService studentCourseService;

    @PostMapping("/add")
    public Result add(@RequestBody StudentCourse studentCourse) {
        studentCourseService.add(studentCourse);
        return Result.success();
    }

    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "ADMIN") String role, @RequestParam(defaultValue = "10") Integer pageSize, StudentCourse studentCourse // ?name=xxx&id=xxx
    ) {
        PageInfo<StudentCourse> pageInfo = studentCourseService.selectPage(pageNum, pageSize, studentCourse);
        return Result.success(pageInfo);
    }

    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id) {
        studentCourseService.deleteById(id);
        return Result.success();
    }

}
