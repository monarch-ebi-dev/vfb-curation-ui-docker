package vfb.ebi.dataingest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Project implements DataIngestResource, Serializable {

    private final String id;
    private List<Dataset> datasets = new ArrayList<>();

    public Project(String id) {
        this.id = id;
    }

    @Override
    public String toJSON() {
        String json = "{ ";
        json = json + "\"id\": \"" + id + "\", ";
        json = json + " }";
        return json;
    }

    public void addDataset(Dataset d) {
        this.datasets.add(d);
    }

    public Iterable<? extends Dataset> getDatasets() {
        return datasets;
    }

    public String getId() {
        return id;
    }

    public void addDatasets(List<Dataset> datasets) {
        this.datasets.addAll(datasets);
    }
}
