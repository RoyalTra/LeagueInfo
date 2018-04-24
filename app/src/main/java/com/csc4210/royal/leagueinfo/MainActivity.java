package com.csc4210.royal.leagueinfo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.csc4210.royal.leagueinfo.utilities.ApiConnectivity;
import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;
import com.csc4210.royal.leagueinfo.utilities.ImageAdapter;
import com.csc4210.royal.leagueinfo.utilities.JReader;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn_champ_list;
    Button btn_compare_champs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button to go to champion list
        btn_champ_list = (Button) findViewById(R.id.btn_champions_list);

        btn_champ_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ChampionsActivity.class);
                startActivity(intent);
            }
        });

        //Button on click go to compare champions
        btn_compare_champs = (Button) findViewById(R.id.btn_compare_champs);

        btn_compare_champs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchForMatchUpsActivity.class);
                startActivity(intent);
            }
        });



//

    }









    //Async Method
//    private class AsyncTaskRunner extends AsyncTask<String, String, JSONObject> {
//
//
//        //Process Thats done in the back ground
//        @Override
//        protected JSONObject doInBackground(String... params){
//
//            return api.getChampsInfo(params[0]);
//
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject result){
//            try {
//                result.getString("Aatrox");
//            }catch(Exception e){
//
//            }
//        }
//
//    }




}
