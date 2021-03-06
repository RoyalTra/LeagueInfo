package com.csc4210.royal.leagueinfo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;


/**
 * Created by Royal on 4/12/2018.
 */

public class ApiConnectivity {

    //Api Related to Riot
    private String api_key = "&api_key=RGAPI-686b4698-3cd2-4808-8c94-9339fae520aa";
    private final String url_base = "https://na1.api.riotgames.com";
    private String url_api_base = null;
    private final String url_static_data = "/lol/static-data/v3";
    private String url_champions = "/champions";
    private final String url_locale ="?locale=en_US";
    private String url_dataById = "&dataById=false";


    //Api Related to Champion.gg
    private String championGG_api_key = "&api_key=?";
    private String championGG_url_base = "http://api.champion.gg/v2/champions";

    //Http Related
    private String url_str;
    private URL url;
    public HttpURLConnection urlConnection;

    //Reading Data
    String json;
    InputStream input;
    BufferedInputStream buffedInput;
    JsonReader jReader;

    //Json object
    JSONObject jObject;

    public ApiConnectivity(){

    }

    //Champion.gg api call
    public JSONObject getChampStats(String champ, String params, Context context){

        String json = "";


        try{
            //Getting champ id from assets
            JReader jReader = new JReader(context);
            JSONObject object = jReader.getInfoForChamp(champ);
            String champID =  object.getString("key");


             url = new URL(championGG_url_base + "/" + champID + "?" +  params + championGG_api_key) ;

            //Log.println(Log.ERROR,"Get Url", url.toString());
            json = getData(url);

            JSONArray jObj = new JSONArray(json);

            jObject = (JSONObject) jObj.get(0);

        }catch(Exception e){
            Log.println(Log.ERROR, "Api Get Stats:", e.toString());
        }finally {

        }

        return jObject;
    }

    private String getData(URL u){
        String json = "";

        try{
            urlConnection = (HttpURLConnection) u.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            input = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader rb = new BufferedReader(new InputStreamReader(input));
            String line;

            while((line = rb.readLine()) != null){
                json += line;
            }


        }catch(Exception e){
            Log.println(Log.ERROR,"get data:", e.toString());
        }finally {

            urlDisconnect();
        }

        return json;

    }






    //JsonObject of Data from the response
    public JSONObject getChampsInfo( String params ){
        String json = "";
        try {
            //Url String
            url = new URL(url_base + url_static_data  +  url_champions + url_locale + params + url_dataById + api_key );

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
