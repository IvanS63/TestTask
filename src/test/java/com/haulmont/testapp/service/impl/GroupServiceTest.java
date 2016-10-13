package com.haulmont.testapp.service.impl;

import com.haulmont.testapp.dao.IGroupDao;
import com.haulmont.testapp.entity.Group;
import org.hsqldb.rights.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


public class GroupServiceTest {
    @Mock
    private IGroupDao groupDao;

    @InjectMocks
    private GroupService groupService;

    @Spy
    private List<Group> groupList = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        initGroups();
    }

    @Test
    public void add() {
        doNothing().when(groupDao).add(any(Group.class));
        groupService.add(any(Group.class));
        verify(groupDao, atLeastOnce()).add(any(Group.class));
    }

    @Test
    public void update() {
        doNothing().when(groupDao).update(any(Group.class));
        groupService.update(any(Group.class));
        verify(groupDao, atLeastOnce()).update(any(Group.class));
    }

    @Test
    public void remove() throws Exception {
        doNothing().when(groupDao).remove(any(Group.class));
        groupService.remove(any(Group.class));
        verify(groupDao, atLeastOnce()).remove(any(Group.class));
    }

    @Test
    public void getAll() {
        when(groupDao.getAll()).thenReturn(groupList);
        Assert.assertEquals(groupService.getAll(), groupList);
    }

    private void initGroups() {
        Group firstGroup = new Group();
        firstGroup.setId(1L);
        firstGroup.setNumber("0000");
        Group secondGroup = new Group();
        secondGroup.setId(2L);
        secondGroup.setNumber("1111");

        groupList.add(firstGroup);
        groupList.add(secondGroup);
    }
}