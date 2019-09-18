package vfb.ebi.idserver.ui;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;

import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("pheno")
@SuppressWarnings("serial")
@Push 
public class PhenoUI extends UI {

	public static String REGISTERDATASET = "Register Dataset";
	public static String REGISTERNEURON = "Register Neuron";
	public static String UPLOADNEURONS = "Upload Neurons";
	Map<String, Layout> views = new HashMap<>();

	@Override
	protected void init(VaadinRequest request) {
		// The root of the component hierarchy
		VaadinSession.getCurrent().getSession().setMaxInactiveInterval(1200); 
		VerticalLayout main = new VerticalLayout();
		setContent(main);
		MenuBar barmenu = new MenuBar();
		main.addComponent(barmenu);
		setWidth("100%");
		final VerticalLayout selection = new VerticalLayout();
		selection.setWidth("100%");
		selection.setMargin(true);
		selection.setSpacing(true);
		main.addComponent(selection);

		// Define a common menu command for all the menu items.
		MenuBar.Command mycommand = new MenuBar.Command() {
			public void menuSelected(MenuItem selectedItem) {
				String menuitem = selectedItem.getText();
				if (menuitem.equals(REGISTERDATASET)) {
					if (!views.containsKey(REGISTERDATASET)) {
						views.put(REGISTERDATASET, new AddDataSetView());
					}
					setNewView(views.get(REGISTERDATASET));
				}
				else if (menuitem.equals(REGISTERNEURON)) {
					if (!views.containsKey(REGISTERNEURON)) {
						views.put(REGISTERNEURON, new AddNeuronView());
					}
					setNewView(views.get(REGISTERNEURON));
				}
				else if (menuitem.equals(UPLOADNEURONS)) {
					if (!views.containsKey(UPLOADNEURONS)) {
						views.put(UPLOADNEURONS, new UploadNeuronsView());
					}
					setNewView(views.get(UPLOADNEURONS));
				}
			}

			private void setNewView(Component c) {
				selection.removeAllComponents();
				selection.addComponent(c);
			}
		};

		barmenu.addItem(REGISTERDATASET, null, mycommand);
		barmenu.addItem(REGISTERNEURON, null, mycommand);
		barmenu.addItem(UPLOADNEURONS, null, mycommand);

	}

	@WebServlet(urlPatterns = "/*", name = "PhenoUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = PhenoUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}