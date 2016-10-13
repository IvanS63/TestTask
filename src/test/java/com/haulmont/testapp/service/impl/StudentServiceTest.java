package com.haulmont.testapp.service.impl;

import com.haulmont.testapp.dao.IStudentDao;
import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.entity.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
    @Mock
    private IStudentDao studentDao;

    @InjectMocks
    private StudentService studentService;

    @Spy
    private List<Student> studentList = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        initStudents();
    }


    @Test
    public void add() {
        doNothing().when(studentDao).add(any(Student.class));
        studentService.add(any(Student.class));
        verify(studentDao, atLeastOnce()).add(any(Student.class));
    }

    @Test
    public void update() {
        doNothing().when(studentDao).update(any(Student.class));
        studentService.update(any(Student.class));
        verify(studentDao, atLeastOnce()).update(any(Student.class));
    }

    @Test
    public void remove() {
        doNothing().when(studentDao).remove(any(Student.class));
        studentService.remove(any(Student.class));
        verify(studentDao, atLeastOnce()).remove(any(Student.class));
    }

    @Test
    public void getAll() {
        when(studentDao.getAll()).thenReturn(studentList);
        Assert.assertEquals(studentService.getAll(), studentList);
    }

    @Test
    public void getAllFilteredByGroup() {
        when(studentDao.getAllFilteredByGroup(anyString())).thenReturn(studentList);
        Assert.assertEquals(studentService.getAllFilteredByGroup(anyString()), studentList);

    }

    @Test
    public void getAllFilteredByLastname() {
        when(studentDao.getAllFilteredByLastname(anyString())).thenReturn(studentList);
        Assert.assertEquals(studentService.getAllFilteredByLastname(anyString()), studentList);
    }

    private void initStudents() {
        Student firstStudent = new Student();
        firstStudent.setFirstName("FirstName");
        firstStudent.setMiddleName("MiddleName");
        firstStudent.setLastName("LastName");
        firstStudent.setBirthDate(new Date());
        Group group = new Group();
        group.setNumber("0000");
        group.setFaculty("Faculty");
        firstStudent.setGroup(group);
        studentList.add(firstStudent);

    }
}