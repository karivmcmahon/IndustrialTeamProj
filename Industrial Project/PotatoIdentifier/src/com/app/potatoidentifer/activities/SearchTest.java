package com.app.potatoidentifer.activities;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class SearchTest extends BaseFragment {
    private Button testBut;
    public static final String MyPREFERENCES = "MyPrefs" ;
	SharedPreferences sharedpreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = this.getActivity();
        final View v = inflater.inflate(R.layout.search_test, container, false);
        final GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(context);
        ds.open();

        testBut = (Button) v.findViewById(R.id.btn_search);
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Log.v("SHARED","SHARED " + sharedpreferences.getString("Date","DEFAULT"));
        testBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new
                        StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                JSONObject json;
                String str = "";
                HttpResponse response;
                HttpClient myClient = new DefaultHttpClient();
                HttpPost myConnection = new HttpPost("https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/sync.php");

                List nameValuePairs = new ArrayList(1);

                Date today = Calendar.getInstance().getTime();
                String currentDate = dateToString(today);
                String lastUpdated = dateToString(today);
               
                nameValuePairs.add(new BasicNameValuePair("currentDate", currentDate));
                nameValuePairs.add(new BasicNameValuePair("lastUpdated", sharedpreferences.getString("Date","DEFAULT")));
                Log.v("SHARED","SHARED " + sharedpreferences.getString("Date","DEFAULT"));
                try {
                    myConnection.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    response = myClient.execute(myConnection);
                    str = EntityUtils.toString(response.getEntity(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    Log.v("TESTing:", str);
                    JSONArray jArray = new JSONArray(str);
                    json = jArray.getJSONObject(0);
                    for(int i = 0; i < jArray.length(); i++) {
                        json = jArray.getJSONObject(i);
                        ds.open();
                        boolean exists = ds.doesDieaseExistByID(json.getString("_id"));
                        Log.v("exists", "exists " + exists);
                        Log.v("ID", "ID: "+json.getString("_id"));
                        if( exists == true) {
                            ds.open();
                            ds.update( json.getString("_id"),json.getString("symptom"), json.getString("type"), json.getString("basicFacts"), json.getString("diagnostics"), json.getString("control"));
                        }
                        else
                        {
                        	 ds.open();
                             ds.insert( json.getString("_id"),json.getString("symptom"), json.getString("type"), json.getString("basicFacts"), json.getString("diagnostics"), json.getString("control"));
                        }
                    }
                } catch ( JSONException e) {
                    e.printStackTrace();
                }
                Editor editor = sharedpreferences.edit();
			    editor.putString("Date", lastUpdated);
			    editor.commit();
                Log.v("SHARED","SHARED " + sharedpreferences.getString("Date","DEFAULT"));
            }
        });
        return v;
    }

    public String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDate = formatter.format(date);
        return currentDate;
    }
}
