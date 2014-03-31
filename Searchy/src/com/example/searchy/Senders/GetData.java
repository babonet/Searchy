package com.example.searchy.Senders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class GetData extends AsyncTask<String, Void, String> {

	private String searchString;
	public GetData(String string) {
		searchString = string;
	}

	@Override
	protected String doInBackground(String... params) {
		HttpResponse response = null;
    	HttpClient client = new DefaultHttpClient();
    	HttpGet get = new HttpGet();
    	
    	try {
			get.setURI(new URI("http://10.0.0.2:64458/api/messages/" + searchString));
			 response = client.execute(get);
			
		} catch (URISyntaxException e) {
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
