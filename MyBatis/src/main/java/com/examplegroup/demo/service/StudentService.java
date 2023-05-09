package com.examplegroup.demo.service;

import com.examplegroup.demo.pojo.Student;

import java.util.List;

public interface StudentService {
    public List<Student> queryAll();
    public void removeById(Integer id);

    public void addStudent(Student student);
    //根据ID查询学生信息
    public Student queryById(Integer id);
    //修改学生信息
    public void changeStudent(Student student);
}
