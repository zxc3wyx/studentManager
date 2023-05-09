package com.examplegroup.demo.dao;

import com.examplegroup.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao {
    public List<Student> selectAll();

    public void deleteById(Integer id);

    public void insertStudent(Student student);

    public Student selectById(Integer id);

    public void updateStudent(Student student);
}
