package vfb.ebi.dataingest.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import vfb.ebi.dataingest.model.*;

import java.util.Optional;


public class ManageDatasetsView extends DataIngestView {
	

	private User u = User.getInstance();
	private UserProjectPanel panel = new UserProjectPanel(u);
	
	
	public ManageDatasetsView(Navigator navigator) {
        super(navigator);

        addComponent(panel);

	}

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
		panel.refresh();
	}

}
