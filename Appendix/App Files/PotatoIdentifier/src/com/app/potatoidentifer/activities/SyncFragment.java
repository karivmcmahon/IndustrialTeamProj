package com.app.potatoidentifer.activities;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.potatoidentifer.models.GlossaryCategoriesDataSource;
import com.example.potatoidentifier.R;

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

public class SyncFragment extends BaseFragment {
	private Button testBut;
	private static final String MyPREFERENCES = "MyPrefs";
	private SharedPreferences sharedpreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		final Context context = getActivity();
		final View v = inflater.inflate(R.layout.search_test, container, false);
		final GlossaryCategoriesDataSource ds = new GlossaryCategoriesDataSource(context);
		ds.open();

		testBut = (Button) v.findViewById(R.id.btn_search);
		sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		final TextView tv = (TextView) v.findViewById(R.id.GlossaryThirdTextTitle);
		tv.setText("Last Updated : " + sharedpreferences.getString("Date", "DEFAULT"));
		testBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkInternetConnection(context)) {
                    Toast.makeText(context, "Updating App....", Toast.LENGTH_LONG).show();
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                    StrictMode.setThreadPolicy(policy);

                    JSONObject json;
                    String str = "";
                    HttpResponse response;
                    HttpClient myClient = new DefaultHttpClient();
                    HttpPost myConnection = new HttpPost("https://zeno.computing.dundee.ac.uk/2014-projects/team1/admin_portal/sync.php");

                    List nameValuePairs = new ArrayList(1);
                    Calendar cal = Calendar.getInstance(); // creates calendar
                    cal.setTime(new Date()); // sets calendar time/date
                    cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
                    Date today = cal.getTime();
                    String lastUpdated = dateToString(today);

                    nameValuePairs.add(new BasicNameValuePair("lastUpdated", sharedpreferences.getString("Date", "DEFAULT")));
                    Log.v("SHARED", "SHARED " + sharedpreferences.getString("Date", "DEFAULT"));
                    try {
                        myConnection.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        response = myClient.execute(myConnection);
                        str = EntityUtils.toString(response.getEntity(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {

                        Toast.makeText(context, "Error with updating the app.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    } catch (ClientProtocolException e) {
                        Toast.makeText(context, "Error with updating the app.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        Toast.makeText(context, "Error with updating the app.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                    try {
                        Log.v("TESTing:", str);
                        JSONArray jArray = new JSONArray(str);
                        if (jArray.length() != 0) {
                            json = jArray.getJSONObject(0);
                            for (int i = 0; i < jArray.length(); i++) {
                                json = jArray.getJSONObject(i);
                                Log.v("testing", Integer.toString(jArray.length()));
                                ds.open();
                                boolean exists = ds.doesDieaseExistByID(json.getString("_id"));
                                Log.v("exists", "exists " + exists);
                                Log.v("ID", "ID: " + json.getString("_id"));
                                if (exists == true) {
                                    ds.open();
                                    ds.update(json.getString("_id"), json.getString("symptom"), json.getString("type"),
                                            json.getString("basicFacts"), json.getString("diagnostics"), json.getString("control"));
                                } else {
                                    ds.open();
                                    Log.v("INSERT", "INSERT");

                                    byte[] image1 = null, image2 = null, image3 = null, image4 = null, image5 = null, image6 = null;

                                    if (!json.getString("imageid").equals(null)) {
                                        image1 = Base64.decode(json.getString("imageid"), Base64.DEFAULT);
                                    }

                                    if (!json.getString("imageid2").equals(null)) {
                                        image2 = Base64.decode(json.getString("imageid2"), Base64.DEFAULT);
                                    }

                                    if (!json.getString("imageid3").equals(null)) {
                                        image3 = Base64.decode(json.getString("imageid3"), Base64.DEFAULT);
                                    }

                                    if (!json.getString("imageid4").equals(null)) {
                                        image4 = Base64.decode(json.getString("imageid4"), Base64.DEFAULT);
                                    }

                                    if (!json.getString("imageid5").equals(null)) {
                                        image5 = Base64.decode(json.getString("imageid5"), Base64.DEFAULT);
                                    }

                                    if (!json.getString("imageid6").equals(null)) {
                                        image6 = Base64.decode(json.getString("imageid6"), Base64.DEFAULT);
                                    }

                                    ds.insert(json.getString("_id"), json.getString("symptom"), json.getString("type"), image1,
                                            image2, image3, image4, image5, image6,
                                            json.getString("basicFacts"), json.getString("diagnostics"), json.getString("control"));
                                }

                            }
                            Editor editor = sharedpreferences.edit();
                            editor.putString("Date", lastUpdated);
                            editor.commit();
                            Log.v("SHARED", "SHARED " + sharedpreferences.getString("Date", "DEFAULT"));
                            Toast.makeText(context, "Update Complete.", Toast.LENGTH_LONG).show();
                            tv.setText("Last Updated : " + sharedpreferences.getString("Date", "DEFAULT"));
                        } else {
                            Toast.makeText(context, "App is already up-to-date.", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(context, "Error with updating the app.", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(context, "We can't connect to the Internet right now.", Toast.LENGTH_LONG).show();
                }
            }
        });
		return v;
	}

	private String dateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = formatter.format(date);
		return currentDate;
	}

	private boolean checkInternetConnection(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
				&& conMgr.getActiveNetworkInfo().isConnected()) {
			return true;
		} else {
			return false;
		}
	}
}
