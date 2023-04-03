package Models;

import java.util.ArrayList;

public class GPTResponse {
	private ArrayList<GPTChoice> choices;
	private String id;
	private String object;
	private int created;
	private String model;
	private Usage usage;
	
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	
	public String getObject() {
		return object;
	}
	
	public void setObject(String object) {
		this.object = object;
	}
	
	public int getCreated() {
		return created;
	}
	
	public void setCreated(int created) {
		this.created = created;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public ArrayList<GPTChoice> getChoices() {
		return choices;
	}
	
	public void setChoices(ArrayList<GPTChoice> choices) {
		this.choices = choices;
	}
	
	public Usage getUsage() {
		return usage;
	}
	
	public void setUsage(Usage usage) {
		this.usage = usage;
	}
	
	
}
