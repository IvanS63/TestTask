package com.haulmont.testapp.ui.window;

import com.haulmont.testapp.ui.MainUI;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Abstract class for creating Add/Edit modal window structure
 */
public abstract class AbstractEditWindow extends Window {

    private static final String OK_BTN_LABEL = "Accept";
    private static final String CANCEL_BRN_LABEL = "Cancel";

    protected final VerticalLayout contentLayout = new VerticalLayout();
    protected final VerticalLayout formLayout = new VerticalLayout();
    protected HorizontalLayout btnLayout;
    protected FieldGroup fieldGroup;

    private final Button okBtn = new Button(OK_BTN_LABEL);
    private final Button cancelBtn = new Button(CANCEL_BRN_LABEL);

    public AbstractEditWindow() {
        initListeners();
        initWindow();
    }

    /**
     * Set modal window properties
     */
    private void initWindow() {
        center();
        setModal(true);
        setResizable(false);
        initLayouts();
    }

    /**
     * Set layouts
     */
    private void initLayouts() {
        btnLayout = new HorizontalLayout(okBtn, cancelBtn);
        contentLayout.addComponents(formLayout, btnLayout);
        setContent(contentLayout);
        contentLayout.setSpacing(true);
        contentLayout.setMargin(true);
        btnLayout.setSpacing(true);

    }

    /**
     * Add listeners to buttons
     */
    private void initListeners() {
        okBtn.addClickListener(e -> okBtnClicked());
        cancelBtn.addClickListener(e -> cancelBtnClicked());
    }

    /**
     * Accept button action
     */
    public abstract void okBtnClicked();

    /**
     * Close window
     */
    public void cancelBtnClicked() {
        close();
    }


}
