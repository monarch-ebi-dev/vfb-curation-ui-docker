package vfb.ebi.dataingest.ui.elements;

import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import vfb.ebi.dataingest.api.APIAccessException;
import vfb.ebi.dataingest.api.DataIngestAPI;
import vfb.ebi.dataingest.model.Dataset;


public class AddDatasetPanel extends VerticalLayout{

	private TextField project = new TextField("Project id");
	private TextField orcid = new TextField("ORCID");
	private TextField shortname = new TextField("Short name");
	private RichTextArea iri = new RichTextArea("IRI");
	private TextField title = new TextField("Title");
	private TextField source_data = new TextField("Link to dataset");
	private Button bt_save = new Button("Save dataset");
	private DataIngestAPI api = DataIngestAPI.getInstance();

	public AddDatasetPanel() {
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

	 void addDataset() {
		Dataset d = new Dataset(shortname.getValue());
		d.setProject(project.getValue());
		d.setOrcid(orcid.getValue());
		d.setSource_data(source_data.getValue());
		d.setTitle(title.getValue());

		try {
			api.addDataset(d);
		} catch (APIAccessException e) {
			e.printStackTrace();
		}

	}

}
