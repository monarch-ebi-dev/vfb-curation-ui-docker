package vfb.ebi.idserver.ui;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opencsv.bean.CsvBindByName;

public class Neuron implements CurationItem, Serializable {

	private String vfb_id = "";
	
	private String state = "";
	
	@CsvBindByName
	private String orcid = "";
	
	@CsvBindByName
	private String project = "";
	
	@CsvBindByName
	private String primary_name = "";
	
	@CsvBindByName
	private String dataset_id = "";
	
	@CsvBindByName
	private String type_specimen = "";
	
	private Set<String> alternative_names = new HashSet<>();
	private Set<String> external_identifiers = new HashSet<>();
	
	@CsvBindByName
	private String classification = "";
	
	@CsvBindByName
	private String classification_comment = "";
	
	@CsvBindByName
	private String url_skeleton_id = "";
	
	@CsvBindByName
	private String template_id = "";
	
	@CsvBindByName
	private String imaging_type = "";

	public Neuron() {
	}

	public String getOrcid() {
		return orcid;
	}

	public void setOrcid(String orcid) {
		this.orcid = orcid;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getPrimary_name() {
		return primary_name;
	}

	public void setPrimary_name(String primary_name) {
		this.primary_name = primary_name;
	}

	public String getDataset_id() {
		return dataset_id;
	}

	public void setDataset_id(String dataset_id) {
		this.dataset_id = dataset_id;
	}

	public String getType_specimen() {
		return type_specimen;
	}

	public void setType_specimen(String type_specimen) {
		this.type_specimen = type_specimen;
	}

	public Set<String> getAlternative_names() {
		return alternative_names;
	}

	public void setAlternative_names(Set<String> alternative_names) {
		this.alternative_names = alternative_names;
	}

	public Set<String> getExternal_identifiers() {
		return external_identifiers;
	}

	public void setExternal_identifiers(Set<String> external_identifiers) {
		this.external_identifiers = external_identifiers;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getClassification_comment() {
		return classification_comment;
	}

	public void setClassification_comment(String classification_comment) {
		this.classification_comment = classification_comment;
	}

	public String getUrl_skeleton_id() {
		return url_skeleton_id;
	}

	public void setUrl_skeleton_id(String url_skeleton_id) {
		this.url_skeleton_id = url_skeleton_id;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getImaging_type() {
		return imaging_type;
	}

	public void setImaging_type(String imaging_type) {
		this.imaging_type = imaging_type;
	}
	
	public String getVfb_id() {
		return vfb_id;
	}

	public void setVfb_id(String vfb_id) {
		this.vfb_id = vfb_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String toJSON() {
		String json = "{ ";
		json = json + "\"orcid\": \"" + orcid + "\", ";
		json = json + "\"project\": \"" + project + "\", ";
		json = json + "\"primary_name\": \"" + primary_name + "\", ";
		json = json + "\"dataset_id\": \"" + dataset_id + "\", ";
		json = json + "\"type_specimen\": \"" + type_specimen + "\", ";
		json = json + "\"classification\": \"" + classification + "\", ";
		json = json + "\"classification_comment\": \"" + classification_comment + "\", ";
		json = json + "\"alternative_names\": [";
		for (String alt : alternative_names) {
			json = json + "\"" + alt + "\", ";
		}
		if (json.endsWith(", ")) {
			json = json.substring(0, json.length() - 2); // replaces the last comma as of above
		}
		json = json + "],";
		json = json + "\"external_identifiers\": [";
		for (String alt : external_identifiers) {
			json = json + "\"" + alt + "\", ";
		}
		if (json.endsWith(", ")) {
			json = json.substring(0, json.length() - 2); // replaces the last comma as of above
		}
		json = json + "],";
		json = json + "\"imaging_type\": \"" + imaging_type + "\", ";
		json = json + "\"template_id\": \"" + template_id + "\", ";
		json = json + "\"url_skeleton_id\": \"" + url_skeleton_id + "\"";
		json = json + " }";
		return json;
	}

}
