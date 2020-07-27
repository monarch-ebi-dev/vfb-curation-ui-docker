package vfb.ebi.dataingest.api;

public class WebAppConfig {

    final String DATAINGESTAPI_URL;
    final String ORCIDAPI_URL;
    final String CLIENT_ID;
    final String CLIENT_SECRET;
    final String LOGIN_REDIRECT_URI;

    final String ORCIDAPI_ENDPOINT_TOKEN;
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
        ORCIDAPI_URL = String.format("%s",System.getenv("orcidapi_url"));
        DATAINGESTAPI_ENDPOINT_DATASET = String.format("%s",System.getenv("dataingest_endpoint_dataset"));
        ORCIDAPI_ENDPOINT_TOKEN = String.format("%s",System.getenv("orcidapi_endpoint_token"));
        DATAINGESTAPI_ENDPOINT_DATASETS = String.format("%s",System.getenv("dataingest_endpoint_datasets"));
        DATAINGESTAPI_ENDPOINT_PROJECT = String.format("%s",System.getenv("dataingest_endpoint_project"));
        DATAINGESTAPI_ENDPOINT_PROJECTS = String.format("%s",System.getenv("dataingest_endpoint_projects"));
        DATAINGESTAPI_ENDPOINT_NEURON = String.format("%s", System.getenv("dataingest_endpoint_neuron"));
        DATAINGESTAPI_ENDPOINT_NEURONS = String.format("%s", System.getenv("dataingest_endpoint_neurons"));
        DATAINGESTAPI_ENDPOINT_LOGIN  = String.format("%s", System.getenv("dataingest_endpoint_login"));
        CLIENT_ID  = String.format("%s",System.getenv("CLIENT_ID"));
        CLIENT_SECRET  = String.format("%s",System.getenv("CLIENT_SECRET"));
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
