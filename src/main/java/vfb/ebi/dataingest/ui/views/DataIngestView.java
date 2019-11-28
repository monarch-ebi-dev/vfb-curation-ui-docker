package vfb.ebi.dataingest.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import vfb.ebi.dataingest.ui.elements.NavigationBar;

public class DataIngestView extends VerticalLayout implements View {

    DataIngestView(Navigator navigator) {
        Layout navi = new NavigationBar(navigator);
        setWidth("100%");
        addComponent(navi);
    }
}
