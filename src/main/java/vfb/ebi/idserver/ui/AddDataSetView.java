package vfb.ebi.idserver.ui;

import java.io.IOException;

import org.json.JSONObject;

import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AddDataSetView extends VerticalLayout{
	
	TextField project = new TextField("Project id");
	TextField orcid = new TextField("ORCID");
	TextField shortname = new TextField("Short name");
	RichTextArea iri = new RichTextArea("IRI");
	private TextField title = new TextField("Title");
	private TextField source_data = new TextField("Link to dataset");
	Button bt_save = new Button("Save dataset");
	CurationAPI api = new CurationAPI();
	
	public AddDataSetView() {
		bt_save.addClickListener(l -> addDataset());
		iri.setEnabled(false);
		iri.setReadOnly(true);
		iri.setWidth("600px");
		this.addComponent(project);
		this.addComponent(orcid);
		this.addComponent(shortname);
		this.addComponent(title);
		this.addComponent(source_data);
		this.addComponent(bt_save);
		this.addComponent(iri);
	}

	private void addDataset() {
		Dataset d = new Dataset(shortname.getValue());
		d.setProject(project.getValue());
		d.setOrcid(orcid.getValue());
		d.setSource_data(source_data.getValue());
		d.setTitle(title.getValue());
		
		String msg;
		try {
			msg = api.addDataSet(d);
			if(msg.startsWith("{")) {
				JSONObject obj = new JSONObject(msg);
				//System.out.println(obj.toString());
				String vfb_id = obj.getString("vfbid");
				msg = "Dataset created with ID: " + vfb_id;
			}
		} catch (IOException e) {
			msg = e.getMessage();
		}
		iri.setValue(msg);
	}

}
