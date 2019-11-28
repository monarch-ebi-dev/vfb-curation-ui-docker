package vfb.ebi.dataingest.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import vfb.ebi.dataingest.api.WebAppConfig;
import vfb.ebi.dataingest.ui.views.LoginView;
import vfb.ebi.dataingest.ui.views.MainView;
import vfb.ebi.dataingest.ui.views.ManageDatasetsView;

@Theme("vfb")
@SuppressWarnings("serial")
@Push
@PushStateNavigation
public class DataIngestUI extends UI {

    Navigator navigator;

    public static final String MANAGEDATASETS = "datasets";
    public static final String LOGIN = "login";
    public static final String MAIN = "";

	@Override
	protected void init(VaadinRequest request) {
		//VaadinSession.getCurrent().getSession().setMaxInactiveInterval(1200);
        getPage().setTitle(WebAppConfig.getInstance().appTitle);

        navigator = new Navigator(this, this);

        navigator.addView(MAIN, new MainView(navigator));
        navigator.addView(MANAGEDATASETS, new ManageDatasetsView(navigator));
        navigator.addView(LOGIN, new LoginView(navigator));
	}



    @WebServlet(urlPatterns = "/*", name = "DataIngestUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = DataIngestUI.class, productionMode = false)
	public static class DataIngestUIServlet extends VaadinServlet {
	}

}