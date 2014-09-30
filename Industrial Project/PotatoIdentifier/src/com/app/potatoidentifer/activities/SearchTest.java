package com.app.potatoidentifer.activities;


import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.potatoidentifer.models.GlossaryCategoriesDataSource;
import com.example.potatoidentifier.R;

public class SearchTest extends BaseFragment{

	private Button testBut;
	static InputStream is = null;
	//  static JSONObject jObj = null;
	  static String json = "";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		final View v = inflater.inflate(R.layout.search_test, container, false);

        Context context = this.getActivity();
        final GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(context);
        ds.open();

        //List<GlossaryCategoriesBean> categories = ds.getGlossaryCategoryInfo();
        //Set sizes of arrays when we know the size of the list.
        testBut = (Button) v.findViewById(R.id.btn_search);
        
        testBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				 StrictMode.ThreadPolicy policy = new   
						    StrictMode.ThreadPolicy.Builder().permitAll().build();
						    StrictMode.setThreadPolicy(policy);
			         
			         
			    
			         
			        JSONObject json = null;
			        String str = "";
			        HttpResponse response;
			        HttpClient myClient = new DefaultHttpClient();
			        HttpPost myConnection = new HttpPost("https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/sync.php");
			         
			        try {
			            response = myClient.execute(myConnection);
			            str = EntityUtils.toString(response.getEntity(), "UTF-8");
			             
			        } catch (ClientProtocolException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			         
			         
			        try{
			            JSONArray jArray = new JSONArray(str);
			            json = jArray.getJSONObject(0);
			             
			         Log.v("json","json " + json);
			         Log.v("id","id" + json.getString("_id"));
			        ds.open();
			        boolean exists = ds.doesDieaseExistByID(json.getString("_id"));
			        Log.v("exists", "exists " + exists);
			        if( exists == true)
			        {
			        	ds.open();
			        	ds.update( json.getString("_id"),json.getString("symptom"), json.getString("type"), json.getString("basicFacts"), json.getString("diagnostics"), json.getString("control"));
			        }
			             
			        } catch ( JSONException e) {
			            e.printStackTrace();               
			        }
			         
				
			} 
			
		});	
        
		return v;

	}
	
	


	
	
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_test);
		
		Button btn = (Button) findViewById(R.id.btn_search);	
		
			
	}*/
	
}
