package com.csc4210.royal.leagueinfo;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Royal on 4/15/2018.
 */

public class JReader {
    Context context;
    AssetManager amanager;

    public JReader(Context context){
        amanager = context.getAssets();
    }

    public JSONObject getInfoForChamp(String champ_name){
        try {
            JSONObject rawData = new JSONObject(readJSONFromAsset(champ_name));
            JSONObject data = (JSONObject) rawData.get("data");
            JSONObject champ_object = (JSONObject) data.get(champ_name);
            return  champ_object;
        }catch(Exception e) {
            Log.println(Log.ERROR, "GetInfoForChamp-Excep", e.toString());
            return null;
        }
    }


    public String readJSONFromAsset(String champ_name) {
        String json = null;
        try {
            InputStream is = amanager.open("champions/" + champ_name + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
