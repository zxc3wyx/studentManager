package com.examplegroup.demo.controller;

import com.examplegroup.demo.pojo.Student;
import com.examplegroup.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService ss;

    //全查
    @RequestMapping("queryAll")
    public List<Student> queryAll() {
        return ss.queryAll();
    }

    //根据ID删除学生信息
    @RequestMapping("removeById")
    public void removeById(@RequestBody Map<String, Object> params, Model model) {
        try {
            ss.removeById((Integer) params.get("id"));
        } catch (Exception e) {
            addExceptionInfo(e, model);
        }
    }
    //添加学生信息
    @RequestMapping("addStudent")
    public void addStudent(@RequestBody Student student, Model model){
        try {
            ss.addStudent(student);
        } catch (Exception e){
            addExceptionInfo(e, model);
        }
    }

    //根据ID查询学生信息
    @RequestMapping("queryById")
    public Student queryById(Integer id, ModelMap mm){
        Student student = ss.queryById(id);
        mm.addAttribute("student",student);
        //跳转到修改页面，进行数据回显
        return student;
    }

    //数据修改
    @RequestMapping("changeStudent")
    public void changeStudent(@RequestBody Student student, Model model){
        try {
            ss.changeStudent(student);
        } catch (Exception e){
            addExceptionInfo(e, model);
        }
    }

    private void addExceptionInfo(Exception e, Model model) {
        model.addAttribute("exceptionInfo", e.getMessage());
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionStackTrace = sw.toString();
        model.addAttribute("exceptionStackTrace", exceptionStackTrace);
    }
}
