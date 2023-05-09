package com.examplegroup.demo.service;

import com.examplegroup.demo.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Student> queryAll() {
        List<Student> students = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("student:*"); // 获取所有键
        for (String key : Objects.requireNonNull(keys)) {
            Student student = new Student();
            student.setId(key.substring(8));
            Map<Object, Object> studentMap = redisTemplate.opsForHash().entries(key);
            student.setName((String) studentMap.get("name"));
            student.setGender((String) studentMap.get("gender"));
            student.setAge((Integer) studentMap.get("age"));
            students.add(student);
        }
        return students;
    }

    @Override
    public void removeById(String id) {
        try {
            redisTemplate.delete("student:" + id);
        } catch (Exception e) {
            throw new RuntimeException("删除学生信息异常！\n异常信息：" + e.getMessage());
        }
    }

    @Override
    public void addStudent(Student student) {
        try {
            String key = "student:" + student.getId();
            redisTemplate.opsForHash().put(key, "name", student.getName());
            redisTemplate.opsForHash().put(key, "age", student.getAge());
            redisTemplate.opsForHash().put(key, "gender", student.getGender());
        } catch (Exception e) {
            throw new RuntimeException("添加学生信息异常！\n异常信息：" + e.getMessage());
        }
    }

    @Override
    public Student queryById(String id) {
        try {
            Student student = new Student();
            student.setId(id);
            Map<Object, Object> studentMap = redisTemplate.opsForHash().entries("student:" + id);
            student.setName((String) studentMap.get("name"));
            student.setGender((String) studentMap.get("gender"));
            student.setAge((Integer) studentMap.get("age"));
            return student;
        } catch (Exception e) {
            throw new RuntimeException("查询id为" + id + "学生信息异常！\n异常信息：" + e.getMessage());
        }
    }

    @Override
    public void changeStudent(Student student) {
        try {
            String key = "student:" + student.getId();
            redisTemplate.opsForHash().put(key, "name", student.getName());
            redisTemplate.opsForHash().put(key, "age", student.getAge());
            redisTemplate.opsForHash().put(key, "gender", student.getGender());
        } catch (Exception e) {
            throw new RuntimeException("修改学生信息异常！\n异常信息：" + e.getMessage());
        }
    }
}
