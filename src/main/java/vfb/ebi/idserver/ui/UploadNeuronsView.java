package vfb.ebi.idserver.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class UploadNeuronsView extends VerticalLayout {

	private TextField external_identifiers = new TextField("Alternative names (seperate by semi-colon)");
	CurationAPI api = new CurationAPI();
	private final Upload upload;
	protected File tempFile;
	protected Grid<Neuron> table;

	public UploadNeuronsView() {
		upload = new Upload("Upload CSV File", new Upload.Receiver() {
			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				try {
					/*
					 * Here, we'll stored the uploaded file as a temporary file. No doubt there's a
					 * way to use a ByteArrayOutputStream, a reader around it, use ProgressListener
					 * (and a progress bar) and a separate reader thread to populate a container
					 * *during* the update. This is quick and easy example, though.
					 */
					tempFile = File.createTempFile("temp", ".csv");
					return new FileOutputStream(tempFile);
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		});

		upload.addFinishedListener(new Upload.FinishedListener() {
			@Override
			public void uploadFinished(Upload.FinishedEvent finishedEvent) {
				try {
					/* Let's build a container from the CSV File */
					FileReader reader = new FileReader(tempFile);
					buildContainerFromCSV(reader);
					reader.close();
					tempFile.delete();

					/* Finally, let's update the table with the container */
					table.setCaption(finishedEvent.getFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		/* Table to show the contents of the file */
		table = new Grid<Neuron>();
		table.setVisible(false);

		/* Main layout */
		this.addComponent(upload);
		this.addComponent(table);
	}

	protected void buildContainerFromCSV(Reader reader) throws IOException {
		CsvToBean<Neuron> csvToBean = new CsvToBeanBuilder(reader).withType(Neuron.class)
				.withIgnoreLeadingWhiteSpace(true).build();

		Iterator<Neuron> csvUserIterator = csvToBean.iterator();
		List<Neuron> neurons = new ArrayList<>();
		while (csvUserIterator.hasNext()) {
			neurons.add(csvUserIterator.next());
		}
		table.setItems(neurons);
		table.addColumn(Neuron::getPrimary_name).setCaption("Name");
		table.addColumn(Neuron::getVfb_id).setCaption("Id");
		table.addComponentColumn(this::buildGetInfoButton);
		table.setSizeFull();
		table.setVisible(true);
		neurons.forEach(this::addNeuron);
	}
	
	private Button buildGetInfoButton(Neuron p) {
        Button button = new Button(VaadinIcons.QUESTION);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        button.addClickListener(e -> show(p.getState()));
        return button;
    }

	private void show(String state) {
		Window sub = new TermInfoWindow(state);
		this.getUI().addWindow(sub);
		this.getUI().push();
	}

	private void addNeuron(Neuron d) {
		String msg;
		try {
			msg = api.addNeuron(d);
			if(msg.startsWith("{")) {
				JSONObject obj = new JSONObject(msg);
				//System.out.println(obj.toString());
				String vfb_id = obj.getString("vfbid");
				d.setVfb_id(vfb_id);
				d.setState("Neurons added successfully!");
			} 
			else {
				d.setVfb_id("failed");
				d.setState(msg);
			}
		} catch (IOException e) {
			msg = e.getMessage();
			// TODO Auto-generated catch block
			d.setVfb_id("failed");
			d.setState(msg);
		}
	}

}
