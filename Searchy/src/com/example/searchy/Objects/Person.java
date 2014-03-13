package com.example.searchy.Objects;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class Person {
	private String personId;
    private String name;
    private ArrayList<String> phones;
    private String email;
    
	public Person() {
		this.personId = "-1";
		this.name = "";
		this.phones = new ArrayList<String>();
		this.email = "";
	}
	
	public ArrayList<String> getPhones() {
		return phones;
	}

	public void addPhone(String phone) {
		this.phones.add(phone);
	}
	
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    public JSONObject ToJSON() throws JSONException{
    	JSONObject json = new JSONObject();
    	json.put("personId", this.personId);
		json.put("phones", this.phones.toString());
		json.put("email", this.email);
		json.put("name", this.name);
    	
    	return json;
    	
    }
    
    

}
