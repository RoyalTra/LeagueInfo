package com.csc4210.royal.leagueinfo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.csc4210.royal.leagueinfo.utilities.ApiConnectivity;
import com.csc4210.royal.leagueinfo.utilities.Champions_Enum;
import com.csc4210.royal.leagueinfo.utilities.ComparePager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CompareChampionsActivity extends AppCompatActivity {

    FrameLayout frame_1;
    FrameLayout frame_2;
    ViewPager viewPager;
    public ComparePager compareAdapter;

    TabLayout tab;
    JSONArray jsonArray;

    ApiConnectivity api = new ApiConnectivity();

    String champ_name;
    Context context;
    String role;
    public  FragmentManager fragmentManager;

    public static ArrayList<JSONObject> matchups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_champions);

        tab  = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        champ_name = bundle.getString("champ_name");
        role = bundle.getString("role");

        new AsyncTaskRunner().execute(CompareChampionsActivity.this);


    }


    public class AsyncTaskRunner extends AsyncTask<Context, String, JSONArray> {
        ProgressBar loading;

        @Override
        protected void onPreExecute(){
            loading = new ProgressBar(getApplicationContext());
            viewPager.addView(loading);
            loading.setIndeterminate(true);
        }


        //Process Thats done in the back ground
        @Override
        protected JSONArray doInBackground(Context... params){

            Log.println(Log.ERROR,"ChampName", champ_name);
            Log.println(Log.ERROR, "ChampID", Champions_Enum.Champ.valueOf(champ_name).getId() + "");



            try {

               JSONArray ja = api.getChampMatchUp(champ_name, "/" + role );
               Log.println(Log.ERROR,"Return", ja.toString());

               return ja;
            }catch(Exception e){


                Log.println(Log.ERROR, "Array", e.toString());
            }

            return null;

        }

        @Override
        protected void onPostExecute(JSONArray result){

            ArrayList<JSONObject> matchups = new ArrayList();

            try{
                int i = 0;

                while(i < 10){
                    Log.println(Log.ERROR, "Hitting", i + "");
                    JSONObject champ = (JSONObject) result.get(i);
                    matchups.add(champ);
                     i++;
                    tab.addTab(tab.newTab());
                }
            }catch (Exception e){
                Log.println(Log.ERROR,"Champion Assign:", e.toString());
                Toast toast = Toast.makeText(getApplicationContext(),"Please make sure your connected to internet.\nStats and Comparision wont work other wise.", Toast.LENGTH_LONG);
                toast.show();
            }


            loading.setIndeterminate(false);
            viewPager.removeView(loading);

            Log.println(Log.ERROR, "BeforeAssignAdapter", "hitting");
            compareAdapter = new ComparePager(getSupportFragmentManager(),matchups.size(),matchups);
            viewPager.setOffscreenPageLimit(3);
            tab.setupWithViewPager(viewPager);
            viewPager.setAdapter(compareAdapter);
            viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));

        }

    }









}
