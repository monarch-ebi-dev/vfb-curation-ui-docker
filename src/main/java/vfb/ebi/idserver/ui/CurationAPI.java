package vfb.ebi.idserver.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class CurationAPI {
	
	private final String CURATIONAPI;
	HttpClient client = new DefaultHttpClient();
	
	public CurationAPI() {
		//this.CURATIONAPI = "http://localhost:5000/api";
		this.CURATIONAPI = System.getenv("CURATIONAPI");
		System.out.println(this.CURATIONAPI);
	}
	
	public String addNeuron(Neuron n) throws ClientProtocolException, IOException {
		StringEntity requestEntity = new StringEntity(n.toJSON(),
			    ContentType.APPLICATION_JSON);
		System.out.println(n.toJSON());
		HttpPost postNeuron = new HttpPost(CURATIONAPI+"/neuron/");
		postNeuron.setEntity(requestEntity);
		HttpResponse response = client.execute(postNeuron);
		  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		  String back = "";
		  String line = "";
		  while ((line = rd.readLine()) != null) {
		   back += line;
		  }	
		  return back;
	}
	
	public String addDataSet(Dataset d) throws ClientProtocolException, IOException {
		System.out.println(d.toJSON());
		StringEntity input = new StringEntity(d.toJSON(),
			    ContentType.APPLICATION_JSON);
		System.out.println(input.toString());
		HttpPost postDataset = new HttpPost(CURATIONAPI+"/dataset/");
		postDataset.setEntity(input);
		HttpResponse response = client.execute(postDataset);
		  BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		  String back = "";
		  String line = "";
		  while ((line = rd.readLine()) != null) {
		   back += line;
		  }	
		  return back;
	}
	

}
