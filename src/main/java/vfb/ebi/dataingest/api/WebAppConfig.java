package vfb.ebi.dataingest.api;

public class WebAppConfig {

    final String DATAINGESTAPI_URL;
    private final String CLIENT_ID;
    private final String LOGIN_REDIRECT_URI;

    final String DATAINGESTAPI_ENDPOINT_DATASET;
    final String DATAINGESTAPI_ENDPOINT_DATASETS;
    final String DATAINGESTAPI_ENDPOINT_PROJECT;
    final String DATAINGESTAPI_ENDPOINT_PROJECTS;
    final String DATAINGESTAPI_ENDPOINT_NEURON;
    final String DATAINGESTAPI_ENDPOINT_NEURONS;
    final String DATAINGESTAPI_ENDPOINT_LOGIN;
    public final String appTitle;
    public final String authoriseURL;

    private static WebAppConfig instance;

    private WebAppConfig() {
        DATAINGESTAPI_URL = String.format("%s",System.getenv("dataingest_url"));
        DATAINGESTAPI_ENDPOINT_DATASET = String.format("%s",System.getenv("dataingest_endpoint_dataset"));
        DATAINGESTAPI_ENDPOINT_DATASETS = String.format("%s",System.getenv("dataingest_endpoint_datasets"));
        DATAINGESTAPI_ENDPOINT_PROJECT = String.format("%s",System.getenv("dataingest_endpoint_project"));
        DATAINGESTAPI_ENDPOINT_PROJECTS = String.format("%s",System.getenv("dataingest_endpoint_projects"));
        DATAINGESTAPI_ENDPOINT_NEURON = String.format("%s", System.getenv("dataingest_endpoint_neuron"));
        DATAINGESTAPI_ENDPOINT_NEURONS = String.format("%s", System.getenv("dataingest_endpoint_neurons"));
        DATAINGESTAPI_ENDPOINT_LOGIN  = String.format("%s", System.getenv("dataingest_endpoint_login"));
        CLIENT_ID  = String.format("%s",System.getenv("client_id"));
        LOGIN_REDIRECT_URI  = String.format("%s",System.getenv("login_redirect_uri"));
        appTitle  = String.format("%s",System.getenv("app_title"));
        authoriseURL = String.format(System.getenv("authorise_url"),CLIENT_ID,LOGIN_REDIRECT_URI);
}

    public static WebAppConfig  getInstance() {
        if(instance==null) {
            instance = new WebAppConfig();
        }
        return instance;
    }
}
