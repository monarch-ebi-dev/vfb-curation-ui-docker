package vfb.ebi.dataingest.ui.views;

import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import vfb.ebi.dataingest.model.Dataset;
import vfb.ebi.dataingest.model.Neuron;

import java.util.Arrays;

public class DatasetPanel extends VerticalLayout {
    public DatasetPanel(Dataset dataset) {

// Create a grid bound to the list
        Grid<Neuron> grid = new Grid<>();
        grid.setItems(dataset.getNeurons());
        grid.addColumn(Neuron::getPrimary_name).setCaption("Name");
        grid.addColumn(Neuron::getClassification).setCaption("Classification");
        addComponent(new Label("Dataset: "+ dataset.getShort_name()));
        addComponent(grid);
        double height = dataset.getNeurons().size()<30 ? dataset.getNeurons().size() : 30;
        grid.setHeightByRows(height);
    }
}
