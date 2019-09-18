package vfb.ebi.idserver.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

//Define a sub-window by inheritance
public class TermInfoWindow extends Window {
	private static final long serialVersionUID = 7544515480410218589L;

	public TermInfoWindow(String state) {
		super(null);
		center();
		setWidth("600px");
		setHeight("500px");
		setModal(true);

		VerticalLayout l = new VerticalLayout();
		l.setWidth("100%");
		l.setHeightUndefined();
		
		RichTextArea msg = new RichTextArea();
		msg.setValue(state);
		msg.setReadOnly(true);
		msg.setWidth("100%");
		l.addComponent(msg);
		Panel c = preparePanel(l, "Information");
		setContent(c);
	}

	private Panel preparePanel(Component c, String label) {
		Panel panel = new Panel(label);
		panel.setWidth("100%");
		panel.setHeight("100%");
		// c.setSizeUndefined();
		c.setWidth("100%");
		panel.setContent(c);
		return panel;
	}
}