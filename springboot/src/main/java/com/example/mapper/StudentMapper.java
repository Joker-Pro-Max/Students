package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
    @Select("select * from students where username=#{username}")
    Student selectByUsername(String username);

    @Insert("insert into students (username, password, name, phone, email, sex, age, birth, avatar, role) " +
            "values (#{username},#{password},#{name},#{phone},#{email},#{sex},#{age},#{birth},#{avatar},#{role})")
    void insert(Student student);
}
