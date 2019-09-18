package vfb.ebi.idserver.ui;

public class Dataset implements CurationItem {

	private String id = "";
	private final String short_name;
	private String title = "";
	private String publication = "";
	private String source_data = "";
	private String orcid = "";
	private String project = "";

	public Dataset(String short_name) {
		this.short_name = short_name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getSource_data() {
		return source_data;
	}

	public void setSource_data(String source_data) {
		this.source_data = source_data;
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

	public String getShort_name() {
		return short_name;
	}

	public String toJSON() {
		String json = "{ ";
		json = json + "\"orcid\": \""+orcid+"\", ";
		json = json + "\"project\": \""+project+"\", ";
		json = json + "\"publication\": \""+publication+"\", ";
		json = json + "\"short_name\": \""+short_name+"\", ";
		json = json + "\"source_data\": \""+source_data+"\", ";
		json = json + "\"title\": \""+title+"\"";
		json = json + " }";
		return json;
	}

}
