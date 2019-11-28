package vfb.ebi.dataingest.api;

import elemental.json.JsonObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WebAppConfig {

    final String DATAINGESTAPI_URL;
    private final String CLIENT_ID;
    private final String LOGIN_REDIRECT_URI;

    final String DATAINGESTAPI_ENDPOINT_DATASET;
    final String DATAINGESTAPI_ENDPOINT_PROJECT;
    final String DATAINGESTAPI_ENDPOINT_NEURON;
    final String DATAINGESTAPI_ENDPOINT_NEURONS;
    final String DATAINGESTAPI_ENDPOINT_LOGIN;
    final String DATAINGESTAPI_ENDPOINT_USER;
    public final String appTitle;
    public final String authoriseURL;

    private static WebAppConfig instance;

    private WebAppConfig() throws IOException, ParseException {
        String configfile = System.getenv("DATAINGESTAPI_CONFIG");
        JSONObject json = parseConfig(configfile);
        DATAINGESTAPI_URL = (String) json.get("dataingest_url");
        DATAINGESTAPI_ENDPOINT_DATASET = (String) json.get("dataingest_endpoint_dataset");
        DATAINGESTAPI_ENDPOINT_PROJECT = String.format("%s",json.get("dataingest_endpoint_project"));
        DATAINGESTAPI_ENDPOINT_NEURON = String.format("%s", json.get("dataingest_endpoint_neuron"));
        DATAINGESTAPI_ENDPOINT_NEURONS = String.format("%s", json.get("dataingest_endpoint_neurons"));
        DATAINGESTAPI_ENDPOINT_LOGIN  = String.format("%s", json.get("dataingest_endpoint_login"));
        DATAINGESTAPI_ENDPOINT_USER = String.format("%s", json.get("dataingest_endpoint_user"));
        CLIENT_ID  = (String) json.get("client_id");
        LOGIN_REDIRECT_URI  = (String) json.get("login_redirect_uri");
        appTitle  = (String) json.get("app_title");
        authoriseURL = String.format((String) json.get("authorise_url"),CLIENT_ID,LOGIN_REDIRECT_URI);
}

    public static WebAppConfig  getInstance() {
        if(instance==null) {
            try {
                instance = new WebAppConfig();
            } catch (Exception e) {
               throw new IllegalStateException("Config could not be created. Make sure config file is readable and correctly formatted!",e);
            }
        }
        return instance;
    }

    private JSONObject parseConfig(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(path));
        return (JSONObject) obj;
    }
}
