package com.haulmont.testapp.dao;

import com.haulmont.testapp.entity.Student;

import javax.persistence.EntityManager;
import java.util.List;

public interface IStudentDao {
    void setEntityManager(EntityManager entityManager);

    void add(Student student);

    void update(Student student);

    void remove(Student student);

    List<Student> getAll();

    List<Student> getAllFilteredByGroup(String value);

    List<Student> getAllFilteredByLastname(String value);
}
