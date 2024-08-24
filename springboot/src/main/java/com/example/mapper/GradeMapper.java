package com.example.mapper;

import com.example.entity.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GradeMapper {
    @Select("select * from grade where del_state=1 and  student_id=#{studentId} and course_id=#{courseId}")
    Grade selectByCondition(Grade grade);


    @Insert("insert into grade(course_id,student_id,score,comment,feedback) " + "values (#{courseId},#{studentId},#{score},#{comment},#{feedback})")
    void insert(Grade grade);

    List<Grade> selectAll(Grade grade);

    @Update("update grade set score=#{score},comment=#{comment},feedback=#{feedback} where id=#{id}")
    void update(Grade grade);

    @Update("update grade set del_state = 0 where id=#{id}")
    void deleteById(Integer id);
}
