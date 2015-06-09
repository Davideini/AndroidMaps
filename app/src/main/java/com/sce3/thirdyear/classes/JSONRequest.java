package com.sce3.thirdyear.classes;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

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

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

public class JSONRequest implements Callable<String> {
    //public final static String SERVER = "192.168.80.1:8081";
    //public final static String SERVER = "10.200.204.243:81";//"10.0.0.138:81"; //"10.200.204.243:81";
    //public final static String SERVER = "10.200.201.176:8080";
    public final static String SERVER = "10.200.204.136:8081";
    //public final static String SERVER = "192.168.1.2:8081";
    //public final static String SERVER = "192.168.1.2:8443";
    //public final static String SERVER = "localhost:8081";//"localhost:8080";
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
        //HttpParams httpParameters=new BasicHttpParams();
        HttpResponse response;
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 5000);
        HttpGet httpGet = new HttpGet(address);
        HttpClient client = new DefaultHttpClient();
        response = client.execute(httpGet);

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
    public HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);


            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 8080));
            registry.register(new Scheme("https", sf, 8443));
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 8081));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }*/
}