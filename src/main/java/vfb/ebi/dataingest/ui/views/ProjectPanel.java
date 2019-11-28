package vfb.ebi.dataingest.ui.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import vfb.ebi.dataingest.model.Dataset;
import vfb.ebi.dataingest.model.Project;

public class ProjectPanel extends VerticalLayout {
    public ProjectPanel(Project project) {
        addComponent(new Label(project.getId()));
        for(Dataset dataset:project.getDatasets()) {
            addComponent(new DatasetPanel(dataset));
        }
    }
}
