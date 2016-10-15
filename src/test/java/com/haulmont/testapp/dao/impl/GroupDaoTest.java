package com.haulmont.testapp.dao.impl;


import com.haulmont.testapp.dao.IGroupDao;
import com.haulmont.testapp.dao.impl.GroupDao;
import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.exception.GroupDeleteException;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GroupDaoTest {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private IGroupDao groupDao;

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
        groupDao = GroupDao.getInstance();
        groupDao.setEntityManager(entityManager);
    }

    @After
    public void afterTest() {
        entityManager.close();
    }

    @Test
    public void add() {
        Group group = new Group();
        group.setNumber("0000");
        group.setFaculty("Faculty");
        groupDao.add(group);
        Assert.assertEquals(group, groupDao.getAll().get(groupDao.getAll().size() - 1));
    }

    @Test
    public void update() {
        Group group = groupDao.getAll().get(0);
        group.setNumber("1111");
        Assert.assertEquals("1111", groupDao.getAll().get(0).getNumber());
    }

    @Test
    public void remove() throws GroupDeleteException {
        int sizeBeforeDelete = groupDao.getAll().size();
        groupDao.remove(groupDao.getAll().get((groupDao.getAll().size() - 1)));
        Assert.assertEquals(sizeBeforeDelete - 1, groupDao.getAll().size());
    }

    @Test(expected = GroupDeleteException.class)
    public void removeException() throws GroupDeleteException {
        groupDao.remove(groupDao.getAll().get(0));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(3,groupDao.getAll().size());
    }
}
