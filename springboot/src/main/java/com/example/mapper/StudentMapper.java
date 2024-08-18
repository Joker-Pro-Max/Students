package com.example.mapper;

import com.example.entity.Course;
import com.example.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {
    @Select("select * from students where username=#{username} and del_state=1")
    Student selectByUsername(String username);

    // 新增
    @Insert("insert into students (username, password, name, phone, email, sex, age, birth, avatar, role) " + "values (#{username},#{password},#{name},#{phone},#{email},#{sex},#{age},#{birth},#{avatar},#{role})")
    void insert(Student student);

    // 分页查询
    @Select("select * from students where del_state=1 and username like concat('%',#{username},'%') and name like concat('%',#{name},'%')" + "order by id desc ")
    List<Student> selectAll(Student student);

    // 删除
    @Update("update students set del_state = 0 where id=#{id}")
    void deleteById(Integer id);

    // 修改
    @Update("update students set username = #{username},password = #{password},name = #{name},phone = #{phone},email = #{email},"
            + "sex = #{sex}, age = #{age}, birth = #{birth}, avatar = #{avatar} where id=#{id}")
    void updateById(Student student);
}
