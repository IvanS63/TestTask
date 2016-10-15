package com.haulmont.testapp.dao.impl;

import com.haulmont.testapp.dao.IStudentDao;
import com.haulmont.testapp.entity.Student;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.List;

public class StudentDao implements IStudentDao {

    private EntityManager entityManager;

    private static final Logger logger = Logger.getLogger(StudentDao.class);

    private final String GET_ALL_QUERY = "SELECT s FROM Student s";
    private final String GET_ALL_FILTERED_BY_LASTNAME = "SELECT s FROM Student s WHERE UPPER(s.lastName) LIKE :value";
    private final String GET_ALL_FILTERED_BY_GROUP = "SELECT s FROM Student s JOIN s.group g WHERE g.number LIKE :value";

    private static IStudentDao instance;

    private StudentDao() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-app-persistence-unit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static IStudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            logger.info("Added student with id=" + student.getId());
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
    }

    @Override
    public void update(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
            logger.info("Updated student with id=" + student.getId());
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
    }

    @Override
    public void remove(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(student);
            entityManager.getTransaction().commit();
            logger.info("Removed student with id=" + student.getId());
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAll() {
        List<Student> studentList = null;
        try {
            entityManager.clear();
            entityManager.getTransaction().begin();
            studentList = entityManager.createQuery(GET_ALL_QUERY).getResultList();
            entityManager.getTransaction().commit();
            logger.info("Received student list");
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
        return studentList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAllFilteredByGroup(String value) {
        List<Student> filteredList = null;
        try {
            entityManager.getTransaction().begin();
            filteredList = entityManager.createQuery(GET_ALL_FILTERED_BY_GROUP)
                    .setParameter("value", "%" + value + "%").getResultList();
            entityManager.getTransaction().commit();
            logger.info("Received group-filtered student list");
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
        return filteredList;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> getAllFilteredByLastname(String value) {
        List<Student> filteredList = null;
        try {
            entityManager.getTransaction().begin();
            filteredList = entityManager.createQuery(GET_ALL_FILTERED_BY_LASTNAME)
                    .setParameter("value","%" + value.toUpperCase() + "%").getResultList();
            entityManager.getTransaction().commit();
            logger.info("Received lastname-filtered student list");
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
        return filteredList;
    }
}
