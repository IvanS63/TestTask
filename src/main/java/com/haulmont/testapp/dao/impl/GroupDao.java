package com.haulmont.testapp.dao.impl;

import com.haulmont.testapp.dao.IGroupDao;
import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.exception.GroupDeleteException;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.hsqldb.HsqlException;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;

public class GroupDao implements IGroupDao {

    private final static Logger logger = Logger.getLogger(GroupDao.class);

    private static IGroupDao instance;

    private EntityManager entityManager;

    private final String GET_ALL_GROUPS_QUERY = "SELECT g FROM Group g";

    private GroupDao() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test-app-persistence-unit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static IGroupDao getInstance() {
        if (instance == null) {
            instance = new GroupDao();
        }
        return instance;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void add(Group group) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(group);
            entityManager.getTransaction().commit();
            logger.info("Added group with id=" + group.getId());
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
    }

    @Override
    public void update(Group group) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(group);
            entityManager.getTransaction().commit();
            logger.info("Updated group with id=" + group.getId());
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
    }

    @Override
    public void remove(Group group) throws GroupDeleteException {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(group);
            entityManager.getTransaction().commit();
            logger.info("Removed group with id=" + group.getId());
        } catch (RollbackException ex) {
            logger.warn("Attempt to delete not empty group id=" + group.getId());
            throw new GroupDeleteException();
        } catch (Exception ex) {
            logger.error(ex);
            entityManager.getTransaction().rollback();
        }


    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Group> getAll() {
        List<Group> groupList = null;
        try {
            entityManager.getTransaction().begin();
            groupList = entityManager.createQuery(GET_ALL_GROUPS_QUERY).getResultList();
            entityManager.getTransaction().commit();
            logger.info("Received group list");
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            logger.error(ex);
        }
        return groupList;
    }
}
