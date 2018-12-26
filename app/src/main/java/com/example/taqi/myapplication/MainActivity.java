package com.example.taqi.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.internal.ANRequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static org.apache.http.params.CoreConnectionPNames.CONNECTION_TIMEOUT;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> usernameList = new ArrayList<>();
    private ArrayList<String> passwordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context;
        new getData().execute();
    }

    private class getData extends android.os.AsyncTask<String, String, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("data1",String.valueOf(s));
            try {
                JSONArray data = new JSONArray(s);
                JSONObject obj = data.getJSONObject(0);
                Log.i("HASIL", obj.getString("username"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RecyclerView recyclerView = findViewById(R.id.recyclerView);
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(usernameList, passwordList, getApplicationContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }

        @Override
        protected String doInBackground(String... strings) {
            final String[] result = new String[1];
            Context context = getApplicationContext();
            RequestQueue queue = Volley.newRequestQueue(context);
            String url = "http://taqi.localhost.run/users";
            final TextView textView = findViewById(R.id.tv);
            StringRequest stringRequest = new StringRequest(url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("KONTOL", response);
                    result[0] = response;

                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("KONTOL", String.valueOf(error));

                }
            });


            stringRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 50000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 50000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
            usernameList.add("w");
            passwordList.add("a");
            queue.add(stringRequest);
            return String.valueOf(result);
        }
    }

}




