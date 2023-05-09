package com.examplegroup.demo.pojo;

import java.io.Serializable;

public class Student implements Serializable {
    private String id;
    private String name;
    private Integer age;
    private String gender;

    public Student() {

    }

    public Student(String id, String name, String gender, Integer age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // 添加一个以 int 作为参数的构造函数
    public Student(String id, String name, String gender, Number age) {
        this(id, name, gender, age.intValue());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
