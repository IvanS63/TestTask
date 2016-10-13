package com.haulmont.testapp.dao.impl;

import com.haulmont.testapp.dao.IStudentDao;
import com.haulmont.testapp.dao.impl.StudentDao;
import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.entity.Student;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

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
    public void add() {
        Student student = new Student();
        student.setGroup(studentDao.getAll().get(0).getGroup());
        student.setFirstName("TestStudentFName");
        student.setMiddleName("TestStudentMName");
        student.setLastName("TestStudentLName");
        student.setBirthDate(new Date());
        studentDao.add(student);
        Assert.assertEquals(student, studentDao.getAll().get(studentDao.getAll().size() - 1));
    }

    @Test
    public void update() {
        Student student = studentDao.getAll().get(0);
        student.setFirstName("NewFirstName");
        studentDao.update(student);
        Assert.assertEquals(student, studentDao.getAll().get(0));
    }

    @Test
    public void remove() {
        int sizeBeforeDelete = studentDao.getAll().size();
        studentDao.remove(studentDao.getAll().get((studentDao.getAll().size() - 1)));
        Assert.assertEquals(sizeBeforeDelete - 1, studentDao.getAll().size());
    }

    @Test
    public void getAll() {
        Assert.assertNotNull(studentDao.getAll());
    }

    @Test
    public void getAllByLastname(){
        Assert.assertEquals(1,studentDao.getAllFilteredByLastname("Petrov").size());
    }

    @Test
    public void getAllByGroup(){
        Assert.assertEquals(1,studentDao.getAllFilteredByGroup("1403").size());
    }
}
