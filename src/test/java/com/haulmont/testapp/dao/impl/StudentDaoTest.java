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

    @BeforeClass
    public static void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("unit-test-persistence-unit");

    }

    @AfterClass
    public static void close() {
        entityManagerFactory.close();
    }

    @Before
    public void beforeTest() {
        entityManager = entityManagerFactory.createEntityManager();
        studentDao = StudentDao.getInstance();
        studentDao.setEntityManager(entityManager);
    }

    @After
    public void afterTest() {
        entityManager.close();
    }

    @Test
    @Ignore
    public void add() {
        Student student = studentDao.getAll().get(1);
        studentDao.add(student);
        List<Student> list=studentDao.getAll();
        Assert.assertEquals(student, studentDao.getAll().get(studentDao.getAll().size() - 1));
    }

    @Ignore
    @Test
    public void update() {
        Student student = studentDao.getAll().get(0);
        student.setFirstName("NewFirstName");
        studentDao.update(student);
        Assert.assertEquals(student, studentDao.getAll().get(0));
    }

    @Ignore
    @Test
    public void remove() {
        int sizeBeforeDelete = studentDao.getAll().size();
        List<Student> list=studentDao.getAll();
        studentDao.remove(studentDao.getAll().get((studentDao.getAll().size() - 1)));
        list=studentDao.getAll();
        Assert.assertEquals(sizeBeforeDelete - 1, studentDao.getAll().size());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3,studentDao.getAll().size());
    }

    @Test
    public void getAllByLastname(){
        Assert.assertEquals(1,studentDao.getAllFilteredByLastname("Petrov").size());
    }

    @Test
    public void getAllByGroup(){
        Assert.assertEquals(1,studentDao.getAllFilteredByGroup("1101").size());
    }
}
