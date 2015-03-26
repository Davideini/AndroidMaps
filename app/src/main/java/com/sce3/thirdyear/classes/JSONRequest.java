package com.sce3.thirdyear.classes;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

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
    public final static String SERVER = "192.168.3.146:8080";
    public final static String IMAGE_DIR = "JavaWeb/images";

    String address;
    Future<String> future;
    public JSONRequest(String address) {
        this.address = address;
        ExecutorService pool = Executors.newFixedThreadPool(1);
        future=pool.submit(this);
    }

    public String getJSON() throws ExecutionException, InterruptedException {
        return future.get();
    }
    public String call() throws IOException {
        return getJSON(address);
    }

    private String getJSON(String address) throws IOException {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        HttpConnectionParams.setConnectionTimeout(new BasicHttpParams(),5000);
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
}