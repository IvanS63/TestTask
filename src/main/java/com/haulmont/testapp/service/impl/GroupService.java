package com.haulmont.testapp.service.impl;

import com.haulmont.testapp.dao.IGroupDao;
import com.haulmont.testapp.dao.impl.GroupDao;
import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.exception.GroupDeleteException;
import com.haulmont.testapp.service.IGroupService;

import java.util.List;

public class GroupService implements IGroupService {

    private static IGroupService instance;

    private IGroupDao groupDao;

    private GroupService() {
        groupDao = GroupDao.getInstance();
    }

    public static synchronized IGroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    @Override
    public void add(Group group) {
        groupDao.add(group);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public void remove(Group group) throws GroupDeleteException {
        try {
            groupDao.remove(group);
        }
        catch (GroupDeleteException ex){
            throw ex;
        }
    }


    @Override
    public List<Group> getAll() {
        return groupDao.getAll();
    }
}
