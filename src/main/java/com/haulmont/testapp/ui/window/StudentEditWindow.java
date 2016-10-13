package com.haulmont.testapp.ui.window;

import com.haulmont.testapp.entity.Group;
import com.haulmont.testapp.entity.Student;
import com.haulmont.testapp.service.IGroupService;
import com.haulmont.testapp.service.IStudentService;
import com.haulmont.testapp.service.impl.GroupService;
import com.haulmont.testapp.service.impl.StudentService;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Modal window class for adding/editing student
 */
public class StudentEditWindow extends AbstractEditWindow {

    private IStudentService studentService;
    private IGroupService groupService;

    private Student student;

    private final static String EDIT_TITLE = "Edit student";
    private final static String ADD_TITLE = "Add new student";

    private static final Logger logger = Logger.getLogger(StudentEditWindow.class);

    /**
     * Form components
     */
    private final TextField firstName = new TextField("First name:");
    private final TextField middleName = new TextField("Middle name:");
    private final TextField lastName = new TextField("Last name:");
    private final DateField birthDate = new DateField("Set birthdate: ");
    private final NativeSelect group = new NativeSelect("Select group: ");

    /**
     * Check if student exists in database
     */
    private boolean isCreated;

    public StudentEditWindow(Student student) {
        super();
        studentService = StudentService.getInstance();
        groupService = GroupService.getInstance();
        if (student == null) {
            this.student = new Student();
            isCreated = false;
            setCaption(ADD_TITLE);
            setFieldsEmptyValues();
        } else {
            this.student = student;
            isCreated = true;
            setCaption(EDIT_TITLE);
        }
        initComponents();
        addFieldValidators();
    }


    @Override
    public void okBtnClicked() {
        try {
            fieldGroup.commit();
            if (isCreated) {
                studentService.update(student);
            } else {
                studentService.add(student);
            }
            close();
        } catch (FieldGroup.CommitException e) {
        }

    }

    private void initComponents() {
        formLayout.addComponent(firstName);
        formLayout.addComponent(middleName);
        formLayout.addComponent(lastName);
        formLayout.addComponent(birthDate);
        formLayout.addComponent(group);

        group.setContainerDataSource(new BeanItemContainer<>(Group.class, groupService.getAll()));

        fieldGroup = new BeanFieldGroup<>(Student.class);
        fieldGroup.setItemDataSource(new BeanItem<>(student));
        fieldGroup.bind(firstName, "firstName");
        fieldGroup.bind(middleName, "middleName");
        fieldGroup.bind(lastName, "lastName");
        fieldGroup.bind(birthDate, "birthDate");
        fieldGroup.bind(group, "group");
        group.setNullSelectionAllowed(false);
        fieldGroup.setBuffered(true);
    }

    private void addFieldValidators() {
        firstName.addValidator(new StringLengthValidator(
                "Must be between 2 and 15 characters in length", 2, 15, false));
        middleName.addValidator(new StringLengthValidator(
                "Must be between 2 and 15 characters in length", 2, 15, false));
        lastName.addValidator(new StringLengthValidator(
                "Must be between 2 and 15 characters in length", 2, 15, false));
        birthDate.addValidator(new NullValidator("Date must be set", false));
        group.addValidator(new NullValidator("Select group from list", false));
    }

    /**
     * Set empty lines to the textboxes instead of default NULL values if new student is created
     */
    private void setFieldsEmptyValues() {
        firstName.setNullRepresentation("");
        middleName.setNullRepresentation("");
        lastName.setNullRepresentation("");
    }
}
