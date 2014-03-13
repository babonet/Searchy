package com.example.searchy.Senders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import com.example.searchy.Objects.Person;
import com.example.searchy.Objects.SMSMessage;

import android.os.AsyncTask;

public class PostData<E>  extends AsyncTask<String, Void, String> {
	
	private ArrayList<E> arrayList;
	
	public PostData(ArrayList<E> al) {
		this.arrayList = al;
	}
 


	@Override
	protected String doInBackground(String... params) {
		HttpResponse response = null;
    	HttpClient client = new DefaultHttpClient();
    	HttpPost req = new HttpPost();
    	
    	try {
			req.setURI(new URI("http://192.168.0.105:64458/api/messages"));
		    req.addHeader("Content-Type", "application/json");
		    for (E al: arrayList){
		    	StringEntity se;
		    	if (al.getClass().getName().toString() == "Person"){
		    		se = new StringEntity(((Person) al).ToJSON().toString());
	                req.setEntity(se);
	                response = client.execute(req);
		    	}
		    	if (al.getClass().getName().toString() == "SMSMessage"){
		    		se = new StringEntity(((SMSMessage) al).ToJSON().toString());
	                req.setEntity(se);
	                response = client.execute(req);
		    	}
		    }
    	} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return response.toString();
	}
	

}