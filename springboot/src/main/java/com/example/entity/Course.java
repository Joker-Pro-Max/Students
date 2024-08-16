package com.example.entity;

public class Course {
    public Integer id;
    public String name;
    public String no;
    public String descr;
    public String teacher;
    public String times;
    public Integer del_state;

    public Integer getDel_state() {
        return del_state;
    }

    public void setDel_state(Integer del_state) {
        this.del_state = del_state;
    }

    public String getTimes() {
        return times;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }


}
