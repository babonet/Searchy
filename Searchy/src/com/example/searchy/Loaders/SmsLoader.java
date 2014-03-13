package com.example.searchy.Loaders;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.example.searchy.Objects.SMSMessage;

public class SmsLoader{
	
	private Context con;

	public SmsLoader(Context con) {
		this.con = con;
	}
    //TODO read only new messages
	public ArrayList<SMSMessage> fetchMessages(){
    	ArrayList<SMSMessage> smsList = new ArrayList<SMSMessage>();
    	SMSMessage smsMsg = new SMSMessage();
    	String columns[] = new String[] {"person", "address", "body", "date","status"};
        String sortOrder = "date ASC"; 
        Uri uriSMSURI = Uri.parse("content://sms/inbox");
        Cursor cur = con.getContentResolver().query(uriSMSURI, columns, null, null,sortOrder);
     
        while (cur.moveToNext()) {
        	smsMsg.setNumber(cur.getString(cur.getColumnIndex(columns[0])));
        	smsMsg.setName(cur.getString(cur.getColumnIndex(columns[1])));
        	smsMsg.setMsg(cur.getString(cur.getColumnIndex(columns[2])));
        	smsMsg.setDate(cur.getString(cur.getColumnIndex(columns[3])));
        	smsMsg.setType(cur.getString(cur.getColumnIndex(columns[4])));
            smsList.add(smsMsg);       
        }
		return smsList;
    }
}
