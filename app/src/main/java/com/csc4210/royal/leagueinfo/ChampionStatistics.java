package com.csc4210.royal.leagueinfo;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Royal on 4/15/2018.
 */

public class ChampionStatistics {

    private String api_key = "&api_key=" + "";
    private String url_base = "api.champion.gg/v2";
    private String url_champ = "/champion";

    //Http Related
    private URL url;
    public HttpURLConnection urlConnection;

    //Reading Data
    InputStream input;
    BufferedInputStream buffedInput;
    JsonReader jReader;

    //Json object
    JSONObject jObject;

    public ChampionStatistics(){}

    public JSONObject getStatistic(){
        String json = "";
        try {
            //Url String
            url = new URL(url_base +  api_key );

            //Connecting and initializing data
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            input = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rb = new BufferedReader(new InputStreamReader(input));
            String line;

            while((line = rb.readLine()) != null){
                json += line;
            }
        }catch (Exception e){
            Log.println(Log.ERROR, "Exception", e.toString());

        }finally {
            urlDisconnect();
        }

        return processData(json);
    }

    //Process the response
    public JSONObject processData(String str){

        try {
            //Read the datd

            JSONObject obj = new JSONObject(str);
            jObject = obj.getJSONObject("data");
            Log.println(Log.ERROR, "Data", jObject.toString());

        }catch(Exception e){
            Log.println(Log.ERROR, "Error", e.toString());

        }

        return jObject;

    }

    //Disconnect Connection
    public void urlDisconnect(){
        urlConnection.disconnect();
    }
}
