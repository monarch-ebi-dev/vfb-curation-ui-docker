package vfb.ebi.idserver.ui;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;

import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class AddNeuronView extends VerticalLayout{
	
	private TextField project = new TextField("Project id");
	private TextField orcid = new TextField("ORCID");
	private TextField primary_name = new TextField("Primary name");
	private TextField dataset_id = new TextField("Dataset id");
	private TextField classification = new TextField("Classification");
	private TextField classification_comment = new TextField("Classification comment");
	private TextField alternative_names = new TextField("Alternative names (seperate by semi-colon)");
	private TextField external_identifiers = new TextField("External identifiers (seperate by semi-colon)");

	RichTextArea iri = new RichTextArea("IRI");
	Button bt_save = new Button("Save dataset");
	CurationAPI api = new CurationAPI();
	
	
	public AddNeuronView() {
		bt_save.addClickListener(l -> addNeuron());
		iri.setEnabled(false);
		iri.setReadOnly(true);
		iri.setWidth("600px");
		this.addComponent(project);
		this.addComponent(orcid);
		this.addComponent(primary_name);
		this.addComponent(dataset_id);
		this.addComponent(classification);
		this.addComponent(classification_comment);
		this.addComponent(alternative_names);
		this.addComponent(external_identifiers);
		this.addComponent(bt_save);
		this.addComponent(iri);
	}

	private void addNeuron() {
		Neuron d = new Neuron();
		d.setPrimary_name(primary_name.getValue());
		d.setProject(project.getValue());
		d.setOrcid(orcid.getValue());
		d.setClassification(classification.getValue());
		d.setClassification_comment(classification_comment.getValue());
		d.setDataset_id(dataset_id.getValue());
		
		Set<String> alternative_names_set = new HashSet<>();
		Arrays.asList(alternative_names.getValue().split(";")).forEach(e->alternative_names_set.add(e.trim()));
		d.setAlternative_names(alternative_names_set);
		
		Set<String> external_identifiers_set = new HashSet<>();
		Arrays.asList(external_identifiers.getValue().split(";")).forEach(e->external_identifiers_set.add(e.trim()));
		d.setExternal_identifiers(external_identifiers_set);
		
		String msg;
		try {
			msg = api.addNeuron(d);
			if(msg.startsWith("{")) {
				JSONObject obj = new JSONObject(msg);
				//System.out.println(obj.toString());
				String vfb_id = obj.getString("vfbid");
				d.setVfb_id(vfb_id);
				d.setState("Neurons added successfully!");
				msg = "Neuron created with ID: " + vfb_id;
			} 
			else {
				d.setVfb_id("failed");
				d.setState(msg);
			}
		} catch (IOException e) {
			msg = e.getMessage();
			// TODO Auto-generated catch block
			d.setVfb_id("failed");
			d.setState(msg);
		}
		iri.setValue(msg);
	}

}
