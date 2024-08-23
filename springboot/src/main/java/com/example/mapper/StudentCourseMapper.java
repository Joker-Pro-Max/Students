package com.example.mapper;

import com.example.entity.StudentCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentCourseMapper {
    @Insert("insert into student_course (name,no,student_id,course_id) values(#{name},#{no},#{student_id},#{course_id})")
    void insert(StudentCourse studentCourse);

    @Select("select * from student_course where del_state=1 and  student_id=#{student_id} and course_id=#{course_id}")
    StudentCourse selectByCondition(StudentCourse studentCourse);

    @Select("select * from student_course where del_state=1 and  name like concat('%',#{name},'%') " + "and no like concat('%',#{no},'%') " + "order by id desc ")
    List<StudentCourse> selectAll(StudentCourse studentCourse);

    @Select("select * from student_course where  del_state=1  and student_id=#{student_id} and name like concat('%',#{name},'%') " + "and no like concat('%',#{no},'%') " + "order by id desc ")
    List<StudentCourse> selectStudentCourse(StudentCourse studentCourse);

    @Update("update student_course set del_state = 0 where id=#{id}")
    void deleteById(Integer id);
}
