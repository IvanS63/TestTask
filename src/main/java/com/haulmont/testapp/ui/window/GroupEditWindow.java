package com.haulmont.testapp.ui.window;

import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.service.IGroupService;
import com.haulmont.testapp.service.impl.GroupService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import org.apache.log4j.Logger;

/**
 * Modal window for adding or editing group
 */
public class GroupEditWindow extends AbstractEditWindow {

    private static final Logger logger = Logger.getLogger(GroupEditWindow.class);

    private final static String EDIT_TITLE = "Edit group";
    private final static String ADD_TITLE = "Add new group";

    private IGroupService groupService;

    private Group group;

    /**
     * Group number to be edited/inserted
     */
    private TextField number = new TextField("Number:");

    /**
     * Group faculty
     */
    private TextField faculty = new TextField("Faculty: ");

    /**
     * Check if group exists in database
     */
    private boolean isCreated;

    public GroupEditWindow(Group group) {
        super();
        groupService = GroupService.getInstance();
        if (group != null) {
            this.group = group;
            isCreated = true;
            setCaption(EDIT_TITLE);
        } else {
            this.group = new Group();
            isCreated = false;
            setCaption(ADD_TITLE);
            setEmptyFieldValues();
        }
        initComponents();
        addFieldValidators();
    }

    private void initComponents() {
        formLayout.addComponent(number);
        formLayout.addComponent(faculty);
        fieldGroup = new BeanFieldGroup<>(Group.class);
        fieldGroup.setItemDataSource(new BeanItem<>(group));
        fieldGroup.bind(number, "number");
        fieldGroup.bind(faculty, "faculty");
    }

    private void addFieldValidators() {
        number.addValidator(new StringLengthValidator(
                "Must be between 2 and 8 characters in length", 2, 8, false));
        faculty.addValidator(new StringLengthValidator(
                "Must be between 4 and 50 characters in length", 4, 50, false));
    }

    private void setEmptyFieldValues() {
        faculty.setNullRepresentation("");
        number.setNullRepresentation("");
    }

    @Override
    public void okBtnClicked() {
        try {
            fieldGroup.commit();
            if (isCreated) {
                groupService.update(group);
            } else {
                groupService.add(group);
            }
            close();
        } catch (FieldGroup.CommitException e) {
        }
    }
}
