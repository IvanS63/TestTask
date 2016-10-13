package com.haulmont.testapp.service.impl;

import com.haulmont.testapp.dao.IStudentDao;
import com.haulmont.testapp.dao.impl.StudentDao;
import com.haulmont.testapp.entity.Student;
import com.haulmont.testapp.service.IStudentService;

import java.util.List;

public class StudentService implements IStudentService {

    private static IStudentService instance;

    private IStudentDao studentDao;

    private StudentService() {
        studentDao = StudentDao.getInstance();
    }

    public static synchronized IStudentService getInstance() {
        if (instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    @Override
    public void add(Student student) {
        studentDao.add(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void remove(Student student) {
        studentDao.remove(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public List<Student> getAllFilteredByGroup(String value) {
        return studentDao.getAllFilteredByGroup(value);
    }

    @Override
    public List<Student> getAllFilteredByLastname(String value) {
        return studentDao.getAllFilteredByLastname(value);
    }
}
