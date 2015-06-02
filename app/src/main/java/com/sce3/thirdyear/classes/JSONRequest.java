package com.sce3.thirdyear.classes;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class JSONRequest implements Callable<String> {
    //public final static String SERVER = "192.168.80.1:8081";
    //public final static String SERVER = "10.200.204.243:81";//"10.0.0.138:81"; //"10.200.204.243:81";
    //public final static String SERVER = "10.200.201.176:8080";
    public final static String SERVER = "localhost:8080";
    public final static String IMAGE_DIR = "JavaWeb/images";

    String address;
    Future<String> future;
    public JSONRequest(String address) {
        this.address = address;
        ExecutorService pool = Executors.newFixedThreadPool(1);
        future = pool.submit(this);
    }

    public String getJSON() throws ExecutionException, InterruptedException {
        return future.get();
    }
    public String call() throws IOException {
        return getJSON(address);
    }

    private String getJSON(String address) throws IOException {
        StringBuilder builder = new StringBuilder();
        HttpParams httpParameters=new BasicHttpParams();
        HttpGet httpGet = new HttpGet(address);
        HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
        HttpConnectionParams.setSoTimeout(httpParameters,5000);
        HttpClient client = new DefaultHttpClient(httpParameters);
        HttpResponse response = client.execute(httpGet);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            throw new ConnectException("Error connecting to server.");
            //Log.e(MainActivity.class.toString(), "Failed to get JSON object");
        }
        return builder.toString();
    }
    /*
    private String postJSON(String address) throws IOException {
        StringBuilder builder = new StringBuilder();
        HttpParams httpParameters=new BasicHttpParams();
        HttpPost httpPost = new HttpPost(address);
        StringEntity se = new StringEntity(/*json to send/*);
        httpPost.setEntity(se);
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(httpPost);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } else {
            throw new ConnectException("Error connecting to server.");
            //Log.e(MainActivity.class.toString(), "Failed to get JSON object");
        }
        return builder.toString();
    }
    */
}