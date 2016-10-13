package com.haulmont.testapp.service;

import com.haulmont.testapp.entity.Student;

import java.util.List;

public interface IStudentService {
    void add(Student student);

    void update(Student student);

    void remove(Student student);

    List<Student> getAll();

    List<Student> getAllFilteredByGroup(String value);

    List<Student> getAllFilteredByLastname(String value);
}
