package com.csc4210.royal.leagueinfo;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChampStatsFragment extends Fragment {

    //Initializing Variables
    View view;
    LinearLayout linear_layout;
    JSONObject champ;
    //Champname
    String champ_name;

    ApiConnectivity api = new ApiConnectivity();

    public ChampStatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_champ_stats, container, false);
        linear_layout = (LinearLayout) view.findViewById(R.id.linear_layout);

        champ_name = getArguments().getString("champ_name");
        // Inflate the layout for this fragment
        new AsyncTaskRunner().execute();
        return view;
    }

    private void initializingPage(){


        //Get ChampID

        JSONObject champion = api.getChampStats(champ_name, "champData=winRate,kda,positions",getActivity());

        Iterator iterator = champion.keys();
        ViewGroup.LayoutParams lParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        while(iterator.hasNext()){
            String key = (String) iterator.next();


            try {
                TextView textView = new TextView(view.getContext());
                textView.setPadding(20,20,20,20);
                textView.setLayoutParams(lParams );
                textView.setText(key.toUpperCase() + ":\t" + champion.getDouble(key));
                linear_layout.addView(textView);
            }catch(Exception e){
                Log.println(Log.ERROR, "While loop", key);

            }

        }

    }

    private class AsyncTaskRunner extends AsyncTask<String, String, JSONObject> {


        //Process Thats done in the back ground
        @Override
        protected JSONObject doInBackground(String... params){

            initializingPage();
            return null;

        }

        @Override
        protected void onPostExecute(JSONObject result){

        }

    }



}
