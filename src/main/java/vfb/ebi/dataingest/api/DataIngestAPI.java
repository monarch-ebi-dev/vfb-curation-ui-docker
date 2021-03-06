package vfb.ebi.dataingest.api;

import java.util.*;

import org.json.JSONException;
import org.json.JSONObject;
import vfb.ebi.dataingest.model.*;

import javax.security.auth.login.FailedLoginException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

public class DataIngestAPI {

    //private HttpClient client = HttpClientBuilder.create().build();
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target(WebAppConfig.getInstance().DATAINGESTAPI_URL);
    private WebTarget orcidWebTarget = client.target(WebAppConfig.getInstance().ORCIDAPI_URL);

    private static DataIngestAPI api = null;

    public static DataIngestAPI getInstance() {
        if (api == null) {
            api = new DataIngestAPI();
        }
        return api;
    }


   /* private String postRequest(String json, String endpoint) throws APIAccessException {
        StringEntity requestEntity = new StringEntity(json,
                ContentType.APPLICATION_JSON);
        System.out.println(json);
        HttpPost post = new HttpPost(endpoint);
        post.setEntity(requestEntity);
        StringBuilder back = new StringBuilder();
        try {
            HttpResponse response = client.execute(post);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = rd.readLine()) != null) {
                back.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new APIAccessException("");
        }
        return back.toString();
    }

    private String postRequest(DataIngestResource n, String endpoint) throws APIAccessException {
        return postRequest(n.toJSON(),endpoint);
    }
*/
    /*
     * PUBLIC API METHODS
     */

    private List<Neuron> getNeurons(String datasetid) {
        return webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_NEURONS)
                .path(String.valueOf(datasetid))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .get(new GenericType<List<Neuron>>() {
                });
    }

    private Neuron getNeuron(String id) {
        return webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_NEURON)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .get(Neuron.class);
    }

    private List<Dataset> getDatasets(String projectid) {
        return webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_DATASETS)
                .path(String.valueOf(projectid))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .get(new GenericType<List<Dataset>>() {
                });
    }

    private Dataset getDataset(String id) {
        return webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_DATASET)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .get(Dataset.class);
    }

    private List<Project> getProjects(String datasetid) {
        return webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_PROJECTS)
                .path(String.valueOf(datasetid))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .get(new GenericType<List<Project>>() {
                });
    }

    private Project getProject(String id) {
        return webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_PROJECT)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .get(Project.class);
    }

    public void login(String code) throws FailedLoginException {
        System.out.println(code);
        try {
            User user = webTarget
                    .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_LOGIN)
                    .queryParam("code", code)
                    .request(MediaType.APPLICATION_JSON)
                    .get(User.class);
            User.getInstance().login(user);
        } catch (Exception e) {
            throw new FailedLoginException();
        }
    }

    public void addNeuron(Neuron n) throws APIAccessException {
        Response r = webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_NEURON)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .post(Entity.entity(n, MediaType.APPLICATION_JSON));
    }

    public void addDataset(Dataset n) throws APIAccessException {
        Response r = webTarget
                .path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_DATASET)
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, User.getInstance().getApiToken())
                .post(Entity.entity(n, MediaType.APPLICATION_JSON));
    }

    public void load_user_data(String code) throws APIAccessException {

        List<Project> userProjects = getProjects(User.getInstance().getId());
        User.getInstance().addProjects(userProjects);

        for (Project p : userProjects) {
            List<Dataset> datasets = getDatasets(p.getId());
            p.addDatasets(datasets);
            for (Dataset d : datasets) {
                //todo neurons should probably be loaded lazily...
                List<Neuron> neurons = getNeurons(d.getId());
                d.addNeurons(neurons);
            }
        }
    }


    public void authenticate2(String code) throws FailedLoginException {
        MultivaluedMap<String, String> formData = new MultivaluedHashMap();
        formData.add("client_id", WebAppConfig.getInstance().CLIENT_ID);
        formData.add("client_secret", WebAppConfig.getInstance().CLIENT_SECRET);
        formData.add("grant_type", "authorization_code");
        formData.add("code", code);
        formData.add("redirect_uri", WebAppConfig.getInstance().LOGIN_REDIRECT_URI);

        Response r = orcidWebTarget
                .path(WebAppConfig.getInstance().ORCIDAPI_ENDPOINT_TOKEN)
                .request().accept(MediaType.APPLICATION_JSON)
                .post(Entity.form(formData));
        if(r.getStatus()==200) {
            System.out.println();
            String orcid_token = r.readEntity(String.class);
            try {
                JSONObject jsonObject = new JSONObject(orcid_token);
            } catch (JSONException err){
                throw new FailedLoginException("ORCID API token was not valid JSON");
            }
        } else {
            throw new FailedLoginException();
        }
    }

    private List<Project> getUserProjects(List<String> projectids) {
        List<Project> userProjects = new ArrayList<>();
        projectids.forEach(id -> userProjects.add(getProject(id)));

        //DESIGN IN SOUCH A WAY THAT A USER OBJECT ALLWAYS COMES WITH REFERENCES TO THEIR PROJECTS.. FROM THERE YOU USE THOSE REFERENCES TO
        // RETRIEVE THE PROJECT METADATA
        Project p1 = new Project("JA01");
        Project p2 = new Project("JZ00");

        Dataset d1 = new Dataset("Zoglu2010");
        Dataset d2 = new Dataset("Zoglu2011");
        Dataset d3 = new Dataset("Zoglu2012");

        Neuron n1 = new Neuron();
        n1.setId("X1");
        n1.setPrimary_name("X1_name");
        n1.setClassification("NEURINL100-");

        Neuron n2 = new Neuron();
        n2.setId("X2");
        n2.setPrimary_name("X2 dd d _name");
        n2.setClassification("ssdsd-");

        d1.addNeuron(n1);
        d1.addNeuron(n2);

        p1.addDataset(d1);
        p1.addDataset(d2);
        p2.addDataset(d3);
        return userProjects;
    }

}
