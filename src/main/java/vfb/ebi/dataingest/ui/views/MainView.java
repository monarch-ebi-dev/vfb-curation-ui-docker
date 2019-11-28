package vfb.ebi.dataingest.ui.views;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import vfb.ebi.dataingest.model.User;

public class MainView extends DataIngestView {

    private Label welcome = new Label();
    private Label token = new Label();
    private Label orcid = new Label();

    public MainView(Navigator navigator) {
        super(navigator);
        addComponent(welcome);
        addComponent(token);
        addComponent(orcid);
        updateUserData();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        updateUserData();
        Notification.show("Hey! "+ User.getInstance().getUsername());
    }

    private void updateUserData() {
        this.welcome.setValue(String.format("Welcome %s",User.getInstance().getUsername()));
        this.token.setValue(String.format("API TOKEN: %s",User.getInstance().getApiToken()));
        this.orcid.setValue(String.format("Orcid: %s",User.getInstance().getOrcid()));
    }
}
