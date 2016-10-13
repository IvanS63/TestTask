package com.haulmont.testapp.service;

import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.exception.GroupDeleteException;

import java.util.List;

public interface IGroupService {
    void add(Group group);

    void update(Group group);

    void remove(Group group) throws GroupDeleteException;

    List<Group> getAll();
}
