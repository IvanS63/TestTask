package com.haulmont.testapp.dao;

import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.exception.GroupDeleteException;

import javax.persistence.EntityManager;
import java.util.List;

public interface IGroupDao {

    void add(Group group);

    void update(Group group);

    void remove(Group group) throws GroupDeleteException;

    List<Group> getAll();

    void setEntityManager(EntityManager entityManager);

}
