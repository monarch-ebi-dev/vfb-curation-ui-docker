package vfb.ebi.dataingest.api;

import java.util.*;

import vfb.ebi.dataingest.model.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DataIngestAPI {

    //private HttpClient client = HttpClientBuilder.create().build();
    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target(WebAppConfig.getInstance().DATAINGESTAPI_URL);
    private WebTarget neuronWebTarget = webTarget.path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_NEURON);
    private WebTarget neuronsWebTarget = webTarget.path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_NEURONS);
    private WebTarget datasetWebTarget = webTarget.path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_DATASET);
    private WebTarget projectWebTarget = webTarget.path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_PROJECT);
    private WebTarget userWebTarget = webTarget.path(WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_USER);

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
    private boolean isValidAPIToken(String response) {
        return false;
    }


    /*
     * PUBLIC API METHODS
     */

    public Neuron getNeurons(String datasetid) {
        return neuronsWebTarget
                .path(String.valueOf(datasetid))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,User.getInstance().getApiToken())
                .get(new GenericType<List<Neuron>>(){});
    }

    public Neuron getNeuron(String id) {
        return neuronWebTarget
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,User.getInstance().getApiToken())
                .get(Neuron.class);
    }

    public Dataset getDataset(String id) {
        return datasetWebTarget
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,User.getInstance().getApiToken())
                .get(Dataset.class);
    }

    public Dataset getProject(String id) {
        return projectWebTarget
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,User.getInstance().getApiToken())
                .get(Dataset.class);
    }

    public User login(String code) {
        return userWebTarget
                .path(String.valueOf(code))
                .queryParam("code",code)
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
    }

    public void addNeuron(Neuron n) throws APIAccessException {
        Response r = neuronWebTarget
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,User.getInstance().getApiToken())
                .post(Entity.entity(n, MediaType.APPLICATION_JSON));
    }

    public void addDataset(Neuron n) throws APIAccessException {
        Response r = datasetWebTarget
                .request(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION,User.getInstance().getApiToken())
                .post(Entity.entity(n, MediaType.APPLICATION_JSON));
    }

    public void login(String code, User user) throws APIAccessException {
        user.setApiToken("asasa");
        user.setOrcid("orcidsaa");
        user.setUsername("Nico");

        List<Project> userProjects = getUserProjects(user);
        user.addProjects(userProjects);
        //todo neurons should probably be loaded lazily...
    }

    private List<Project> getUserProjects(User user) {
        List<Project> userProjects = new ArrayList<>();

        try {
           userProjects.addAll(parseProjects(postRequest(user,WebAppConfig.getInstance().DATAINGESTAPI_ENDPOINT_PROJECT)));
        } catch (APIAccessException e) {
            e.printStackTrace();
        }

        //DESIGN IN SOUCH A WAY THAT A USER OBJECT ALLWAYS COMES WITH REFERENCES TO THEIR PROJECTS.. FROM THERE YOU USE THOSE REFERENCES TO
        // RETRIEVE THE PROJECT METADATA
        Project p1 = new Project("JA01");
        Project p2 = new Project("JZ00");

        Dataset d1 = new Dataset("Zoglu2010");
        Dataset d2 = new Dataset("Zoglu2011");
        Dataset d3 = new Dataset("Zoglu2012");

        Neuron n1 = new Neuron();
        n1.setVfb_id("X1");
        n1.setPrimary_name("X1_name");
        n1.setClassification("NEURINL100-");

        Neuron n2 = new Neuron();
        n2.setVfb_id("X2");
        n2.setPrimary_name("X2 dd d _name");
        n2.setClassification("ssdsd-");

        d1.addNeuron(n1);
        d1.addNeuron(n2);

        p1.addDataset(d1);
        p1.addDataset(d2);
        p2.addDataset(d3);
        return userProjects;
    }

    private Collection<? extends Project> parseProjects(String postRequest) {
        return null;
    }

}
