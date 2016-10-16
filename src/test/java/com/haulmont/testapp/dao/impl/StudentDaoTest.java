package com.haulmont.testapp.dao.impl;

import com.haulmont.testapp.dao.IStudentDao;
import com.haulmont.testapp.dao.impl.StudentDao;
import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.entity.Student;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StudentDaoTest {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private IStudentDao studentDao;

    @Before
    public void beforeTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("unit-test-persistence-unit");
        entityManager = entityManagerFactory.createEntityManager();
        studentDao = StudentDao.getInstance();
        studentDao.setEntityManager(entityManager);
    }

    @After
    public void afterTest() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void add() {
        Student student = studentDao.getAll().get(1);
        studentDao.add(student);
        Assert.assertEquals(3, studentDao.getAll().size());
    }

    @Test
    public void update() {
        Student student = studentDao.getAll().get(0);
        student.setFirstName("NewFirstName");
        studentDao.update(student);
        Assert.assertEquals("NewFirstName", studentDao.getAll().get(0).getFirstName());
    }

    @Test
    public void remove() {
        studentDao.remove(studentDao.getAll().get(2));
        Assert.assertEquals(2, studentDao.getAll().size());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3, studentDao.getAll().size());
    }

    @Test
    public void getAllByLastname() {
        Assert.assertEquals(1, studentDao.getAllFilteredByLastname("Petrov").size());
    }

    @Test
    public void getAllByGroup() {
        Assert.assertEquals(1, studentDao.getAllFilteredByGroup("1101").size());
    }
}
