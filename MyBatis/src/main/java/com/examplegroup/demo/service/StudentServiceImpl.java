package com.examplegroup.demo.service;

import com.examplegroup.demo.dao.StudentDao;
import com.examplegroup.demo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao dao;

    @Override
    public List<Student> queryAll() {
        return dao.selectAll();
    }

    @Override
    public void removeById(Integer id) {
        try {
            dao.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("根据id删除学生信息异常！\n异常信息：" + e.getMessage());
        }
    }

    @Override
    public void addStudent(Student student) {
        try {
            dao.insertStudent(student);
        } catch (Exception e) {
            throw new RuntimeException("添加学生信息异常！\n异常信息：" + e.getMessage());
        }
    }

    @Override
    public Student queryById(Integer id) {
        return dao.selectById(id);
    }

    @Override
    public void changeStudent(Student student) {
        try {
            dao.updateStudent(student);
        } catch (Exception e) {
            throw new RuntimeException("修改学生信息异常！\n异常信息：" + e.getMessage());
        }
    }
}
