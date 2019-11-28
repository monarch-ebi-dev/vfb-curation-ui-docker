package vfb.ebi.dataingest.ui.elements;

import com.vaadin.ui.Button;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.json.JSONObject;
import vfb.ebi.dataingest.api.APIAccessException;
import vfb.ebi.dataingest.api.DataIngestAPI;
import vfb.ebi.dataingest.model.Dataset;
import vfb.ebi.dataingest.model.Neuron;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class AddNeuronPanel extends VerticalLayout{

	private TextField project = new TextField("Project id");
	private TextField orcid = new TextField("ORCID");
	private TextField primary_name = new TextField("Primary name");
	private TextField dataset_id = new TextField("Dataset id");
	private TextField classification = new TextField("Classification");
	private TextField classification_comment = new TextField("Classification comment");
	private TextField alternative_names = new TextField("Alternative names (seperate by semi-colon)");
	private TextField external_identifiers = new TextField("External identifiers (seperate by semi-colon)");

	private RichTextArea iri = new RichTextArea("IRI");
	private Button bt_save = new Button("Save dataset");
	private DataIngestAPI api = DataIngestAPI.getInstance();

	public AddNeuronPanel() {
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

		;
		try {
			api.addNeuron(d);
		} catch (APIAccessException e) {
			String msg = e.getMessage();
			// TODO Auto-generated catch block
			d.setId("failed");
			d.setState(msg);
		}
		iri.setValue(d.getState());
	}

}
