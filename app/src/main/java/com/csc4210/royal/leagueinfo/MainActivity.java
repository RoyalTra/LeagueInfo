package com.csc4210.royal.leagueinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ApiConnectivity api;
    TextView txt;
    JSONObject jObject;
    GridView grid;
    ImageView[] img_array = new ImageView[Champions_Enum.getSize()];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (GridView) findViewById(R.id.gridView);
        ImageAdapter imgadapter = new ImageAdapter(this);
        grid.setAdapter(imgadapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Toast.makeText(MainActivity.this , "" + position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, ChampionProfileActivity.class);

                //Using bundle to pass variable to the next activity
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


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
