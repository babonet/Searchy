package com.example.searchy.Objects;

import org.json.JSONException;
import org.json.JSONObject;

public class SMSMessage {
	private String address;
    private String personId;
    private String date;
    private String msg;
    private String type;
    
	public SMSMessage(String number, String name, String date, String msg,
			String type) {
		this.address = number;
		this.personId = name;
		this.date = date;
		this.msg = msg;
		this.type = type;
	}
	public SMSMessage() {
		this.address = "";
		this.personId = "";
		this.date = "";
		this.msg = "";
		this.type = "";

	}
	public String getNumber() {
		return address;
	}
	public void setNumber(String number) {
		this.address = number;
	}
	public String getName() {
		return personId;
	}
	public void setName(String name) {
		this.personId = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public JSONObject ToJSON() throws JSONException{
    	JSONObject json = new JSONObject();
    	json.put("personId", this.personId);
		json.put("msg", this.msg);
		json.put("date", this.date);
		json.put("type", this.type);
		json.put("address", this.type);
    	
    	return json;
    	
    }
    

}
