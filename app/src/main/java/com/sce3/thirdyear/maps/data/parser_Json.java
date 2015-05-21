package com.sce3.thirdyear.maps.data;

import android.util.Log;

import com.sce3.thirdyear.classes.JSONRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by David on 21/05/2015.
 */
public class parser_Json {

    public static JSONObject getJSONfromURL(String url) {

        url = url.replaceAll("[' ']", "%20");

        // initialize
        InputStream is = null;
        String result = "";
        JSONObject jObject = null;
        JSONRequest json = null;
        // http post
        try {
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpGet httppost = new HttpGet(url);
//            HttpResponse response = httpclient.execute(httppost);
//            HttpEntity entity = response.getEntity();
//            is = entity.getContent();
            //json = new JSONRequest(url);
            json = new JSONRequest(url);
            jObject = new JSONObject(json.getJSON());
        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }

        return jObject;
    }

}
