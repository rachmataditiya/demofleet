package com.logtrac.maxsum.logtrac.connection;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by MuhammadAnwar on 10/24/2017.
 */

public class CallmeConnection {
    private String CallmeUrl="https://track.primeforcindo.com/api/callme?";
    public static String CallmeMESSAGE="";
    public static boolean ISCallmeFINISHED=false;

    private String session_id;

    public CallmeConnection(String sesid) {
        this.session_id=sesid;
    }


    public void Callme() {
        new CallmeConnection.CallmeAsyncTask().execute();
    }
    public String getUrl() {
        return this.CallmeUrl;
    }

    private class CallmeAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            urls.getClass();
            return postCallmeData(session_id);
        }
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), "Data Sent!", Toast.LENGTH_LONG).show();
        }
    }

    private String postCallmeData(String sid){
        InputStream inputStream = null;
        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(CallmeUrl+"session_id="+sid);
            HttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            if(entity != null) {
                result = EntityUtils.toString(entity);
            }
            else
                result = "error";
        } catch (Exception e) {
            result = "error : "+e.toString();
        }

        CallmeConnection.CallmeMESSAGE=result;
        CallmeConnection.ISCallmeFINISHED=true;
        return result;
    }
}
