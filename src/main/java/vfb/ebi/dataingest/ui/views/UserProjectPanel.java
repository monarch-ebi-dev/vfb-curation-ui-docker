package vfb.ebi.dataingest.ui.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import vfb.ebi.dataingest.model.Dataset;
import vfb.ebi.dataingest.model.Project;
import vfb.ebi.dataingest.model.User;

public class UserProjectPanel extends VerticalLayout {

    private final User u;


    UserProjectPanel(User u) {
        this.u = u;
        refresh();
    }

    public void refresh() {
        removeAllComponents();
        addComponent(new Label("Projects for "+u.getUsername()));
        for (Project project:u.getProjects()) {
            addComponent(new ProjectPanel(project));
        }
    }

}
